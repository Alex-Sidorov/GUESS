package com.guess.service;

import com.guess.model.Picture;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface PictureService {

    Page<Picture> getPictures(int page, int size);

    Page<Picture> getPictures(UUID userId, int page, int size);

    Picture getPicture(UUID pictureId);

    Picture uploadPicture(MultipartFile file);

    void deletePicture(UUID pictureId);

}
