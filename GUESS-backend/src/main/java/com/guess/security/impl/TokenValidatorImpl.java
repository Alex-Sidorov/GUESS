package com.guess.security.impl;

import com.guess.configuration.AppConfiguration;
import com.guess.security.TokenValidator;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;

import static io.jsonwebtoken.io.Decoders.BASE64;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenValidatorImpl implements TokenValidator {

    private final AppConfiguration appConfiguration;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(BASE64.decode(appConfiguration.getJwt().getBase64Secret()));
    }

    @Override
    public boolean validateAccessToken(String accessToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(accessToken);
            return true;
        } catch (JwtException e) {
            log.debug("Invalid access token!");
        }

        return false;
    }

    @Override
    public boolean validateRefreshToken(String refreshToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(refreshToken);
            return true;
        } catch (JwtException e) {
            log.debug("Invalid refresh token!");
        }

        return false;
    }

}
