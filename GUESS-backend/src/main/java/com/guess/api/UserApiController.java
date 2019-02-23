package com.guess.api;

import com.guess.controller.UserApi;
import com.guess.model.User;
import com.guess.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.UUID;

import static com.guess.util.HeaderUtil.createPaginationHeaders;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
public class UserApiController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<List<User>> getUsers(
            @Min(1) @Valid @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @Min(10) @Max(30) @Valid @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {

        final Page<User> usersPage = userService.getUsers(page, size);
        final HttpHeaders headers = createPaginationHeaders(usersPage, getUsersPath);
        return new ResponseEntity<>(usersPage.getContent(), headers, OK);
    }

    @Override
    public ResponseEntity<User> getUser(@PathVariable("userId") UUID userId) {

        final User user = userService.getUser(userId);
        return new ResponseEntity<>(user, OK);
    }

}
