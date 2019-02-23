package com.guess.util.converter;

import com.guess.entity.UserEntity;
import com.guess.model.SignupRequest;
import com.guess.model.User;
import lombok.experimental.UtilityClass;

import static com.guess.entity.enums.UserRole.USER;

@UtilityClass
public class UserConverter {

    public static UserEntity toUserEntity(SignupRequest signupRequest, String hashPassword) {

        return UserEntity.builder()
                .firstName(signupRequest.getFirstName())
                .lastName(signupRequest.getLastName())
                .email(signupRequest.getEmail())
                .hashPassword(hashPassword)
                .role(USER)
                .build();
    }

    public static User toUserModel(UserEntity userEntity) {

        return new User()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail());
    }

    public static UserEntity updateUser(UserEntity target, User source) {

        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        return target;
    }

}
