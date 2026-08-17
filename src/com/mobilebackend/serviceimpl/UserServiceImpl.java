package com.mobilebackend.serviceimpl;

import com.mobilebackend.exception.UserNotFoundException;
import com.mobilebackend.model.User;
import com.mobilebackend.repository.UserRepository;
import com.mobilebackend.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl
        implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(
            UserRepository repository) {

        this.repository = repository;
    }


    @Override
    public User register(User user) {

        validate(user);

        return repository.save(user);
    }


    @Override
    public Optional<User> findById(int id) {

        return repository.findById(id);
    }


    @Override
    public User update(User user) {

        validate(user);

        if (repository.findById(user.getId()).isEmpty()) {

            throw new UserNotFoundException(
                    user.getId()
            );
        }

        repository.update(user);

        return user;
    }


    @Override
    public void delete(int id) {

        if (repository.findById(id).isEmpty()) {

            throw new UserNotFoundException(id);
        }

        repository.delete(id);
    }


    @Override
    public List<User> search(String keyword) {

        return repository.search(keyword);
    }


    private void validate(User user) {

        if (user == null) {

            throw new IllegalArgumentException(
                    "User cannot be null"
            );
        }

        if (user.getName() == null ||
                user.getName().isBlank()) {

            throw new IllegalArgumentException(
                    "Name is required"
            );
        }

        if (user.getEmail() == null ||
                user.getEmail().isBlank()) {

            throw new IllegalArgumentException(
                    "Email is required"
            );
        }

        if (user.getPassword() == null ||
                user.getPassword().isBlank()) {

            throw new IllegalArgumentException(
                    "Password is required"
            );
        }
    }
}