package com.guess.api;

import com.guess.controller.UserApi;
import com.guess.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
public class UserApiController implements UserApi {

    @Override
    public ResponseEntity<List<User>> getUsers(
            @Min(1) @Valid @RequestParam(value = "page", required = false, defaultValue="1") Integer page,
            @Min(10) @Max(30) @Valid @RequestParam(value = "size", required = false, defaultValue="10") Integer size) {

        return null;
    }

    @Override
    public ResponseEntity<User> getUser(UUID userId) {

        return null;
    }

}
