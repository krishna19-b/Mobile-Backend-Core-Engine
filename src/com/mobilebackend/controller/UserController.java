package com.mobilebackend.controller;

import com.mobilebackend.model.User;
import com.mobilebackend.service.UserService;

public class UserController {

    private final UserService service;

    public UserController(
            UserService service) {

        this.service = service;
    }


    public User register(String name, String email, String password, String phone) {

        User user = new User(name, email, password, phone);

        return service.register(user);
    }


    public void findUser(int id) {

        service.findById(id)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println(
                                "User not found"
                        )
                );
    }


    public void updateUser(User user) {

        System.out.println(
                service.update(user)
        );
    }


    public void deleteUser(int id) {

        service.delete(id);

        System.out.println(
                "User deleted"
        );
    }


    public void searchUsers(
            String keyword) {

        service.search(keyword)
                .forEach(System.out::println);
    }
}