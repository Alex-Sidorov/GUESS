package com.guess.service;

import com.guess.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ImageService {

    Page<Image> getAllImages(int page, int size);

    Page<Image> getUserImages(UUID userId, int page, int size);

    Image getImage(UUID imageId);

    Image uploadImage(MultipartFile file);

    void deleteImage(UUID ImageId);

}
