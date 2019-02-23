package com.guess.service.impl;

import com.guess.entity.UserEntity;
import com.guess.exception.ConflictException;
import com.guess.exception.NotFoundException;
import com.guess.model.ChangePasswordRequest;
import com.guess.model.Picture;
import com.guess.model.User;
import com.guess.respository.UserRepository;
import com.guess.service.ProfileService;
import com.guess.util.converter.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.guess.util.SecurityUtility.getUserId;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User getProfile() {

        return userRepository.findOneById(getUserId())
                .map(UserConverter::toUserModel)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @Override
    @Transactional
    public User updateProfile(User updateRequest) {

        return userRepository.findOneById(getUserId())
                .map(existing -> UserConverter.updateUser(existing, updateRequest))
                .map(userRepository::saveAndFlush)
                .map(UserConverter::toUserModel)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequest request) {

        final Optional<UserEntity> user = userRepository.findOneById(getUserId());
        if(user.isPresent() && passwordEncoder.matches(request.getCurrentPassword(), user.get().getHashPassword())) {
            user.get().setHashPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.saveAndFlush(user.get());
        } else {
            throw new ConflictException("Password don't match!");
        }
    }

    @Override
    @Transactional
    public Page<Picture> getProfilePictures(int page, int size) {

        return null;
    }

}
