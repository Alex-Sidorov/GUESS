package com.guess.service;

import org.springframework.web.multipart.MultipartFile;

public interface AmazonS3Service {

    void upload(MultipartFile file, String filename, String bucketName);

    String getFileUrl(String filename, String bucketName, String publicHost);

    void delete(String filename, String bucketName);

}
