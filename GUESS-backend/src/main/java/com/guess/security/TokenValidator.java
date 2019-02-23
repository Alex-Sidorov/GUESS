package com.guess.security;

public interface TokenValidator {

    boolean validateAccessToken(String accessToken);

    boolean validateRefreshToken(String refreshToken);

}
