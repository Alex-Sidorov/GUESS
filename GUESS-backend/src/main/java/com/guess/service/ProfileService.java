package com.guess.service;

import com.guess.model.ChangePasswordRequest;
import com.guess.model.Image;
import com.guess.model.User;
import org.springframework.data.domain.Page;

public interface ProfileService {

    User getProfile();

    User updateProfile(User updateRequest);

    void changePassword(ChangePasswordRequest request);

    Page<Image> getProfileImages(int page, int size);

}
