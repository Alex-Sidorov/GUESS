package com.guess.api;

import com.guess.controller.ProfileApi;
import com.guess.model.ChangePasswordRequest;
import com.guess.model.Picture;
import com.guess.model.User;
import com.guess.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

import static com.guess.util.HeaderUtil.createPaginationHeaders;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class ProfileApiController implements ProfileApi {

    private final ProfileService profileService;

    @Override
    public ResponseEntity<User> getProfile() {

        final User user = profileService.getProfile();
        return new ResponseEntity<>(user, OK);
    }

    @Override
    public ResponseEntity<User> updateProfile(@Valid @RequestBody User updateRequest) {

        final User user = profileService.updateProfile(updateRequest);
        return new ResponseEntity<>(user, OK);
    }

    @Override
    public ResponseEntity<Void> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) {

        profileService.changePassword(changePasswordRequest);
        return new ResponseEntity<>(OK);
    }

    @Override
    public ResponseEntity<List<Picture>> getProfilePictures(
            @Min(1) @Valid @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @Min(10) @Max(30) @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {

        final Page<Picture> picturesPage = profileService.getProfilePictures(page, size);
        final HttpHeaders headers = createPaginationHeaders(picturesPage, getProfilePicturesPath);
        return new ResponseEntity<>(picturesPage.getContent(), headers, OK);
    }

}
