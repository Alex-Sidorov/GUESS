package com.guess.security;

import com.guess.security.impl.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TokenAuthenticationProvider implements AuthenticationProvider {

    private final TokenProvider tokenProvider;
    private final TokenValidator tokenValidator;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final TokenAuthentication tokenAuthentication = (TokenAuthentication) authentication;
        final String accessToken = tokenAuthentication.getName();

        if(tokenValidator.validateAccessToken(accessToken)) {
            final UserDetails userDetails = new UserDetailsImpl(tokenProvider.buildUserEntityByToken(accessToken));

            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
        } else {
            tokenAuthentication.setAuthenticated(false);
        }

        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
