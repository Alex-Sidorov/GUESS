package com.guess.service.impl;

import com.guess.model.SigninRequest;
import com.guess.model.SignupRequest;
import com.guess.model.TokenModel;
import com.guess.model.User;
import com.guess.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public User signUp(SignupRequest request) {

        return null;
    }

    @Override
    public TokenModel signIn(SigninRequest request) {

        return null;
    }

}
