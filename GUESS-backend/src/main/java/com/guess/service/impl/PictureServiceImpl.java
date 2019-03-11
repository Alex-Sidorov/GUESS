package com.guess.service.impl;

import com.guess.exception.NotFoundException;
import com.guess.model.Picture;
import com.guess.respository.PictureRepository;
import com.guess.service.AmazonService;
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

    private final AmazonService amazonService;
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

        return pictureRepository.findById(pictureId)
                .map(PictureConverter::toPictureModel)
                .orElseThrow(() -> new NotFoundException("Picture not found!"));
    }

    @Override
    @Transactional
    public Picture uploadPicture(MultipartFile file) {

        final UUID userId = getUserId();
        final String filename = createFilename(userId.toString(), UUID.randomUUID().toString(), file.getContentType());

        amazonService.uploadToAmazon(file, filename);
        final String pictureUrl = amazonService.getFileUrl(filename);
        return toPictureModel(pictureRepository.saveAndFlush(toPictureEntity(userId, pictureUrl)));
    }

    @Override
    @Transactional
    public void deletePicture(UUID pictureId) {

        pictureRepository.softDeleteById(pictureId);
    }

    @SneakyThrows
    private String createFilename(String prefix, String key, String contentType) {

        final String ext = mimeTypes.forName(contentType).getExtension();
        return prefix + "/" + key + ext;
    }

}
