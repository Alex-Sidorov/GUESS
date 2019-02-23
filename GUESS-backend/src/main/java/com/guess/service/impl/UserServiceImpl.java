package com.guess.service.impl;

import com.guess.model.User;
import com.guess.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Override
    public Page<User> getUsers(int page, int size) {

        return null;
    }

    @Override
    public User getUser(UUID userId) {

        return null;
    }
}
