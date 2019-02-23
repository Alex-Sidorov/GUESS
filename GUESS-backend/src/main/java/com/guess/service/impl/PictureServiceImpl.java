package com.guess.service.impl;

import com.guess.model.Picture;
import com.guess.service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {

    @Override
    public Page<Picture> getPictures(int page, int size) {

        return null;
    }

    @Override
    public Page<Picture> getPictures(UUID userId, int page, int size) {

        return null;
    }

    @Override
    public Picture getPicture(UUID pictureId) {

        return null;
    }

    @Override
    public Picture uploadPicture(MultipartFile file) {

        return null;
    }

    @Override
    public void deletePicture(UUID pictureId) {


    }

}
