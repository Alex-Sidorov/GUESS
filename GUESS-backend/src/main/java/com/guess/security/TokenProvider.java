package com.guess.security;

import com.guess.entity.UserEntity;
import com.guess.model.TokenModel;

public interface TokenProvider {

    TokenModel createTokenModel(UserEntity userEntity);

    TokenModel refreshToken(String refreshToken);

    UserEntity buildUserEntityByToken(String token);

}
