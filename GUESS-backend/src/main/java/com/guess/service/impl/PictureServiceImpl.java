package com.guess.service.impl;

import com.guess.exception.NotFoundException;
import com.guess.model.Picture;
import com.guess.respository.PictureRepository;
import com.guess.service.AmazonS3Service;
import com.guess.service.PictureService;
import com.guess.util.converter.PictureConverter;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tika.mime.MimeTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.guess.util.SecurityUtility.getUserId;
import static com.guess.util.converter.PictureConverter.toPictureEntity;
import static com.guess.util.converter.PictureConverter.toPictureModel;

@Service
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final AmazonS3Service amazonS3Service;
    private final PictureRepository pictureRepository;

    private final MimeTypes mimeTypes = MimeTypes.getDefaultMimeTypes();

    @Override
    @Transactional
    public Page<Picture> getPictures(int page, int size) {

        return pictureRepository.findAllSortedByCreation(PageRequest.of(page - 1, size))
                .map(PictureConverter::toPictureModel);
    }

    @Override
    @Transactional
    public Page<Picture> getPictures(UUID userId, int page, int size) {

        return pictureRepository.findAllByUserIdSortedByCreation(userId, PageRequest.of(page - 1, size))
                .map(PictureConverter::toPictureModel);
    }

    @Override
    @Transactional
    public Picture getPicture(UUID pictureId) {

        return pictureRepository.findOneById(pictureId)
                .map(PictureConverter::toPictureModel)
                .orElseThrow(() -> new NotFoundException("Picture not found!"));
    }

    @Override
    @Transactional
    public Picture uploadPicture(MultipartFile file) {

        final UUID userId = getUserId();
        final String filename = createFilename(UUID.randomUUID().toString(), file.getContentType());

        amazonS3Service.uploadToAmazon(file, filename);
        final String pictureUrl = amazonS3Service.getFileUrl(filename);
        return toPictureModel(pictureRepository.saveAndFlush(toPictureEntity(userId, pictureUrl)));
    }

    @Override
    @Transactional
    public void deletePicture(UUID pictureId) {

        pictureRepository.softDeleteById(pictureId);
    }

    @SneakyThrows
    private String createFilename(String key, String contentType) {

        final String ext = mimeTypes.forName(contentType).getExtension();
        return key + ext;
    }

}
