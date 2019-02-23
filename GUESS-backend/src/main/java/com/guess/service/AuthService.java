package com.guess.service;

import com.guess.model.SigninRequest;
import com.guess.model.SignupRequest;
import com.guess.model.TokenModel;
import com.guess.model.User;

public interface AuthService {

    User signUp(SignupRequest request);

    TokenModel signIn(SigninRequest request);

}
