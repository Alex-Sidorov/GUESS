package com.guess.service.impl;

import com.guess.entity.UserEntity;
import com.guess.exception.ConflictException;
import com.guess.exception.UnauthorizedException;
import com.guess.model.*;
import com.guess.repository.UserRepository;
import com.guess.security.TokenProvider;
import com.guess.security.TokenValidator;
import com.guess.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.guess.entity.enums.UserRoleEntity.USER;
import static com.guess.mapper.UserMapper.USER_MAPPER;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenProvider tokenProvider;
    private final TokenValidator tokenValidator;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User signUp(SignupRequest request) {
        if (userRepository.findOneByEmail(request.getEmail()).isPresent()) {
            throw new ConflictException("User with the same email already exist!");
        }
        final UserEntity user = USER_MAPPER.toEntity(request);
        user.setHashPassword(encoder.encode(request.getPassword()));
        user.setRole(USER);
        final UserEntity savedUser = userRepository.saveAndFlush(user);
        return USER_MAPPER.toModel(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public TokenModel signIn(SigninRequest request) {
        final Optional<UserEntity> user = userRepository.findOneByEmail(request.getEmail());
        if (user.isPresent() && encoder.matches(request.getPassword(), user.get().getHashPassword())) {
            return tokenProvider.createTokenModel(user.get());
        }
        throw new UnauthorizedException("User with this credentials was not found!");
    }

    @Override
    public TokenModel refreshToken(RefreshTokenRequest request) {
        if (tokenValidator.validateRefreshToken(request.getRefreshToken())) {
            return tokenProvider.refreshToken(request.getRefreshToken());
        }
        throw new UnauthorizedException("Invalid refresh token!");
    }

}
