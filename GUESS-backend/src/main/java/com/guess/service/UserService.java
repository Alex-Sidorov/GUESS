package com.guess.service;

import com.guess.model.User;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface UserService {

    Page<User> getUsers(int page, int size);

    User getUser(UUID userId);

}
