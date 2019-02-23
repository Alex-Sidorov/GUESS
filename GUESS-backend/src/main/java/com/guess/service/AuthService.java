package com.guess.service;

import com.guess.model.*;

public interface AuthService {

    User signUp(SignupRequest request);

    TokenModel signIn(SigninRequest request);

    TokenModel refreshToken(RefreshTokenRequest request);

}
