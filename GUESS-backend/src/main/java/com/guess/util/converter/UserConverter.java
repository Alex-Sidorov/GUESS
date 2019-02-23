package com.guess.util.converter;

import com.guess.entity.UserEntity;
import com.guess.model.SignupRequest;
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
}
