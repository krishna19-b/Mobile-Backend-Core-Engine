package com.mobilebackend.serviceimpl;

import com.mobilebackend.exception.AuthenticationException;
import com.mobilebackend.model.User;
import com.mobilebackend.repository.UserRepository;
import com.mobilebackend.service.AuthenticationService;

public class AuthenticationServiceImpl
        implements AuthenticationService {

    private final UserRepository repository;

    public AuthenticationServiceImpl(
            UserRepository repository) {

        this.repository = repository;
    }


    @Override
    public User login(
            String email,
            String password) {

        User user =
                repository.findByEmail(email)
                        .orElseThrow(
                                () ->
                                        new AuthenticationException(
                                                "Invalid email or password"
                                        )
                        );

        if (!user.getPassword()
                .equals(password)) {

            throw new AuthenticationException(
                    "Invalid email or password"
            );
        }

        return user;
    }
}