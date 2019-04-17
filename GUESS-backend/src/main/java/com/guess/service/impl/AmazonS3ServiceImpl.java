package com.guess.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.transfer.TransferManager;
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
    private final TransferManager transferManager;

    @Override
    @SneakyThrows
    public void upload(MultipartFile file, String filename, String bucketName) {
        final ObjectMetadata metadata = buildObjectMetadata(file);
        final PutObjectRequest request = new PutObjectRequest(bucketName, filename, file.getInputStream(), metadata);
        transferManager.upload(request);
    }

    @Override
    @SneakyThrows
    public String getFileUrl(String filename, String bucketName, String publicHost) {
        final URL originUrl = amazonS3.getUrl(bucketName, filename);
        return UriComponentsBuilder.fromUri(originUrl.toURI())
                .scheme("https")
                .host(publicHost)
                .build()
                .toUriString();
    }

    @Override
    public void delete(String filename, String bucketName) {
        amazonS3.deleteObject(bucketName, filename);
    }

    private ObjectMetadata buildObjectMetadata(MultipartFile file) {
        final ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        return metadata;
    }

}
