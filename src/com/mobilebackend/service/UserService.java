package com.mobilebackend.service;

import com.mobilebackend.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User register(User user);

    Optional<User> findById(int id);

    User update(User user);

    void delete(int id);

    List<User> search(String keyword);
}