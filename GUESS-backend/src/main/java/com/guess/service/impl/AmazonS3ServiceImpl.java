package com.guess.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.guess.configuration.AppConfiguration;
import com.guess.service.AmazonS3Service;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;

@Service
@AllArgsConstructor
public class AmazonS3ServiceImpl implements AmazonS3Service {

    private final AmazonS3 amazonS3;
    private final AppConfiguration appConfiguration;

    @Override
    @SneakyThrows
    public void uploadToAmazon(MultipartFile file, String filename) {

        final ObjectMetadata metadata = buildObjectMetadata(file);
        amazonS3.putObject(appConfiguration.getAmazon().getS3().getBucketName(),
                filename, file.getInputStream(), metadata);
    }

    @Override
    @SneakyThrows
    public String getFileUrl(String filename) {

        final URL originUrl = amazonS3.getUrl(appConfiguration.getAmazon().getS3().getBucketName(), filename);
        return UriComponentsBuilder.fromUri(originUrl.toURI())
                .scheme("https")
                .host(appConfiguration.getAmazon().getCloudFront().getHostName())
                .build()
                .toUriString();
    }

    private ObjectMetadata buildObjectMetadata(MultipartFile file) {

        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        return metadata;
    }

}
