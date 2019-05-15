package com.guess.mapper;

import com.guess.entity.ImageEntity;
import com.guess.model.Image;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ImageMapper {

    ImageMapper IMAGE_MAPPER = getMapper(ImageMapper.class);

    Image toModel(ImageEntity source);

}
