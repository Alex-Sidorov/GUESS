package com.guess.api;

import com.guess.controller.AuthApi;
import com.guess.model.*;
import com.guess.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@AllArgsConstructor
public class AuthApiController implements AuthApi {

    private final AuthService authService;

    @Override
    public ResponseEntity<User> signUp(@Valid @RequestBody SignupRequest signupRequest) {
        final User user = authService.signUp(signupRequest);
        return new ResponseEntity<>(user, CREATED);
    }

    @Override
    public ResponseEntity<TokenModel> signIn(@Valid @RequestBody SigninRequest signinRequest) {
        final TokenModel tokenModel = authService.signIn(signinRequest);
        return new ResponseEntity<>(tokenModel, CREATED);
    }

    @Override
    public ResponseEntity<TokenModel> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshRequest) {
        final TokenModel tokenModel = authService.refreshToken(refreshRequest);
        return new ResponseEntity<>(tokenModel, CREATED);
    }

}
