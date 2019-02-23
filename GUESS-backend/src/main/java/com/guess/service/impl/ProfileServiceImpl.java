package com.guess.service.impl;

import com.guess.model.ChangePasswordRequest;
import com.guess.model.Picture;
import com.guess.model.User;
import com.guess.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    @Override
    public User getProfile() {

        return null;
    }

    @Override
    public User updateProfile(User updateRequest) {

        return null;
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {

    }

    @Override
    public Page<Picture> getProfilePictures(int page, int size) {

        return null;
    }
}
