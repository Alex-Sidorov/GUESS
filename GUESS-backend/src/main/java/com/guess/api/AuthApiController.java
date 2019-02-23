package com.guess.api;

import com.guess.controller.AuthApi;
import com.guess.model.SigninRequest;
import com.guess.model.SignupRequest;
import com.guess.model.TokenModel;
import com.guess.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthApiController implements AuthApi {

    @Override
    public ResponseEntity<User> signUp(@Valid @RequestBody SignupRequest signupRequest) {

        return null;
    }

    @Override
    public ResponseEntity<TokenModel> signIn(@Valid @RequestBody SigninRequest signinRequest) {

        return null;
    }

}
