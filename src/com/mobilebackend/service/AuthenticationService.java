package com.mobilebackend.service;

import com.mobilebackend.model.User;

public interface AuthenticationService {

    User login(
            String email,
            String password);
}