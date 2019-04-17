package com.guess.service.impl;

import com.guess.configuration.AppConfiguration;
import com.guess.entity.ImageEntity;
import com.guess.entity.UserEntity;
import com.guess.exception.NotFoundException;
import com.guess.exception.UnprocessableEntityException;
import com.guess.model.Image;
import com.guess.repository.ImageRepository;
import com.guess.service.AmazonS3Service;
import com.guess.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.guess.mapper.ImageMapper.IMAGE_MAPPER;
import static com.guess.util.SecurityUtility.getUserId;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {

    private static final Map<String, String> IMAGES_MEDIA_TYPE_EXTENSION = new HashMap<String, String>() {{
        put("image/jpeg", ".jpg");
        put("image/png", ".png");
    }};

    private final AppConfiguration appConfiguration;
    private final AmazonS3Service amazonS3Service;
    private final ImageRepository imageRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Image> getAllImages(int page, int size) {
        return imageRepository.findAllSortedByCreation(PageRequest.of(page - 1, size))
                .map(IMAGE_MAPPER::toModel);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Image> getUserImages(UUID userId, int page, int size) {
        return imageRepository.findAllByUserIdSortedByCreation(userId, PageRequest.of(page - 1, size))
                .map(IMAGE_MAPPER::toModel);
    }

    @Override
    @Transactional(readOnly = true)
    public Image getImage(UUID imageId) {
        return imageRepository.findOneById(imageId)
                .map(IMAGE_MAPPER::toModel)
                .orElseThrow(() -> new NotFoundException("Picture not found!"));
    }

    @Override
    @Transactional
    public Image uploadImage(MultipartFile file) {
        final UUID userId = getUserId();
        final String extension = tryParseContentType(file.getContentType());
        final String filename = UUID.randomUUID().toString() + extension;
        final String bucketName = appConfiguration.getAmazon().getS3().getImagesBucketName();
        final String publicHost = appConfiguration.getAmazon().getCloudFront().getImagesUrl();

        amazonS3Service.upload(file, filename, bucketName);
        final String imageUrl = amazonS3Service.getFileUrl(filename, bucketName, publicHost);
        final ImageEntity image = ImageEntity.builder()
                .filename(filename)
                .url(imageUrl)
                .user(UserEntity.builder().id(userId).build())
                .build();
        final ImageEntity savedImage = imageRepository.saveAndFlush(image);
        return IMAGE_MAPPER.toModel(savedImage);
    }

    @Override
    @Transactional
    public void deleteImage(UUID imageId) {
        final String bucketName = appConfiguration.getAmazon().getS3().getImagesBucketName();
        imageRepository.findOneById(imageId)
                .ifPresent(image -> {
                    amazonS3Service.delete(image.getFilename(), bucketName);
                    imageRepository.softDeleteById(imageId);
                });
    }

    private String tryParseContentType(String mediaType) {
        final String extension = IMAGES_MEDIA_TYPE_EXTENSION.get(mediaType);
        if (extension == null) {
            throw new UnprocessableEntityException("This media type is not supported!");
        }
        return extension;
    }

}
