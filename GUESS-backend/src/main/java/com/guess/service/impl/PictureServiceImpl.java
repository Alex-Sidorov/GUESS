package com.guess.service.impl;

import com.guess.model.Picture;
import com.guess.service.PictureService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PictureServiceImpl implements PictureService {

    @Override
    @Transactional
    public Page<Picture> getPictures(int page, int size) {

        return null;
    }

    @Override
    @Transactional
    public Page<Picture> getPictures(UUID userId, int page, int size) {

        return null;
    }

    @Override
    @Transactional
    public Picture getPicture(UUID pictureId) {

        return null;
    }

    @Override
    @Transactional
    public Picture uploadPicture(MultipartFile file) {

        return null;
    }

    @Override
    @Transactional
    public void deletePicture(UUID pictureId) {


    }

}
