package com.guess.service.impl;

import com.guess.service.AmazonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class AmazonServiceImpl implements AmazonService {

    @Override
    public void uploadToAmazon(MultipartFile file, String filename) {

    }

    @Override
    public String getFileUrl(String filename) {
        return null;
    }
}
