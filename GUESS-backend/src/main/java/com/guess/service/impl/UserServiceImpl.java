package com.guess.service.impl;

import com.guess.exception.NotFoundException;
import com.guess.model.User;
import com.guess.repository.UserRepository;
import com.guess.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.guess.mapper.UserMapper.USER_MAPPER;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public Page<User> getUsers(int page, int size) {
        return userRepository.findAllSortedByCreation(PageRequest.of(page - 1, size))
                .map(USER_MAPPER::toModel);
    }

    @Override
    @Transactional
    public User getUser(UUID userId) {
        return userRepository.findOneById(userId)
                .map(USER_MAPPER::toModel)
                .orElseThrow(() -> new NotFoundException("User not found!"));
    }

}
