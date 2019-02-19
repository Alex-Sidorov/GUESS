package com.guess.security.impl;

import com.guess.configuration.AppConfiguration;
import com.guess.entity.UserEntity;
import com.guess.entity.enums.UserRole;
import com.guess.model.TokenModel;
import com.guess.security.TokenProvider;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

import static io.jsonwebtoken.io.Decoders.BASE64;

@Component
@RequiredArgsConstructor
public class TokenProviderImpl implements TokenProvider {

    private static final int MILLIS_IN_SECOND = 1000;
    private static final String ROLE_KEY = "rol";
    private static final String TYPE_KEY = "typ";
    private static final String ACCESS_TOKEN = "access";
    private static final String REFRESH_TOKEN = "refresh";

    private final AppConfiguration appConfiguration;

    private Key key;
    private long accessTokenValidityMillis;
    private long refreshTokenValidityMillis;

    @PostConstruct
    public void init() {

        this.key = Keys.hmacShaKeyFor(BASE64.decode(appConfiguration.getJwt().getBase64Secret()));
        this.accessTokenValidityMillis = appConfiguration.getJwt().getAccessTokenValidity() * MILLIS_IN_SECOND;
        this.refreshTokenValidityMillis = appConfiguration.getJwt().getRefreshTokenValidity() * MILLIS_IN_SECOND;
    }

    @Override
    public TokenModel createTokenModel(UserEntity userEntity) {

        final long currentTimeMillis = System.currentTimeMillis();
        final Date issuedAt = new Date(currentTimeMillis);

        final Date accessExpiresIn = new Date(currentTimeMillis + accessTokenValidityMillis);
        final Date refreshExpiresIn = new Date(currentTimeMillis + refreshTokenValidityMillis);

        final String accessToken = createToken(userEntity, issuedAt, accessExpiresIn, ACCESS_TOKEN);
        final String refreshToken = createToken(userEntity, issuedAt, refreshExpiresIn, REFRESH_TOKEN);

        return new TokenModel()
                .accessToken(accessToken)
                .accessIssuedAt(issuedAt.getTime())
                .accessExpiresIn(accessExpiresIn.getTime())
                .refreshToken(refreshToken)
                .refreshIssuedAt(issuedAt.getTime())
                .refreshExpiresIn(refreshExpiresIn.getTime());
    }

    @Override
    public TokenModel refreshToken(String refreshToken) {

        final long currentTimeMillis = System.currentTimeMillis();
        final Date accessIssuedAt = new Date(currentTimeMillis);
        final Date refreshIssuedAt = Jwts.parser().setSigningKey(key).parseClaimsJws(refreshToken).getBody().getIssuedAt();

        final Date accessExpiresIn = new Date(currentTimeMillis + accessTokenValidityMillis);
        final Date refreshExpiresIn = Jwts.parser().setSigningKey(key).parseClaimsJws(refreshToken).getBody().getExpiration();

        final String accessToken = createToken(getUserIdByToken(refreshToken), getUserRoleByToken(refreshToken),
                accessIssuedAt, accessExpiresIn, ACCESS_TOKEN);

        return new TokenModel()
                .accessToken(accessToken)
                .accessIssuedAt(accessIssuedAt.getTime())
                .accessExpiresIn(accessExpiresIn.getTime())
                .refreshToken(refreshToken)
                .refreshIssuedAt(refreshIssuedAt.getTime())
                .refreshExpiresIn(refreshExpiresIn.getTime());
    }

    @Override
    public UserEntity buildUserEntityByToken(String token) {

        return UserEntity.builder()
                .id(getUserIdByToken(token))
                .role(getUserRoleByToken(token))
                .build();
    }

    private String createToken(UserEntity userEntity, Date issuedAt, Date expiresIn, String tokenType) {

        return createToken(userEntity.getId(), userEntity.getRole(), issuedAt, expiresIn, tokenType);
    }

    private String createToken(UUID userId, UserRole userRole, Date issuedAt, Date expiresIn, String tokenType) {

        final JwtBuilder jwtBuilder =  Jwts.builder();

        jwtBuilder.setSubject(userId.toString());
        jwtBuilder.setIssuedAt(issuedAt);
        jwtBuilder.setExpiration(expiresIn);
        jwtBuilder.claim(TYPE_KEY, tokenType);
        jwtBuilder.claim(ROLE_KEY, userRole.toString());

        jwtBuilder.signWith(key, SignatureAlgorithm.HS512);
        return jwtBuilder.compact();
    }

    private UUID getUserIdByToken(String token) {

        return UUID.fromString(Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject());
    }

    private UserRole getUserRoleByToken(String token) {

        return UserRole.valueOf((String)Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().get(ROLE_KEY));
    }

}
