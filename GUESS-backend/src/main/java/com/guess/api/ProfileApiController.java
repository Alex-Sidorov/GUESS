package com.guess.api;

import com.guess.controller.ProfileApi;
import com.guess.model.ChangePasswordRequest;
import com.guess.model.Picture;
import com.guess.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProfileApiController implements ProfileApi {

    @Override
    public ResponseEntity<User> getProfile() {

        return null;
    }

    @Override
    public ResponseEntity<User> updateProfile(@Valid @RequestBody User updateRequest) {

        return null;
    }

    @Override
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {

        return null;
    }

    @Override
    public ResponseEntity<List<Picture>> getProfilePictures(
            @Min(1) @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page,
            @Min(10) @Max(30) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {

        return null;
    }

}
