package com.guess.service;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3Service {

    void uploadToAmazon(MultipartFile file, String filename);

    String getFileUrl(String filename);

}
