package com.guess.mapper;

import com.guess.entity.UserEntity;
import com.guess.model.SignupRequest;
import com.guess.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface UserMapper {

    UserMapper USER_MAPPER = getMapper(UserMapper.class);

    User toModel(UserEntity source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hashPassword", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(SignupRequest source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "hashPassword", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity updateEntity(User source, @MappingTarget UserEntity target);

}
