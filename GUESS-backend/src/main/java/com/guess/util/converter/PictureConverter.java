package com.guess.util.converter;

import com.guess.entity.PictureEntity;
import com.guess.model.Picture;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class PictureConverter {

    public static Picture toPictureModel(PictureEntity pictureEntity) {

        return new Picture()
                .id(pictureEntity.getId())
                .url(pictureEntity.getUrl());
    }

    public static PictureEntity toPictureEntity(UUID userId, String url) {

        return PictureEntity.builder()
                .userId(userId)
                .url(url)
                .build();
    }

}
