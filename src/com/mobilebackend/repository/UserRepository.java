package com.mobilebackend.repository;

import com.mobilebackend.dao.UserDAO;
import com.mobilebackend.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepository {

    private final UserDAO dao;

    public UserRepository(UserDAO dao) {

        this.dao = dao;
    }

    public User save(User user) {

        try {

            return dao.save(user);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public Optional<User> findById(int id) {

        try {

            return dao.findById(id);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public Optional<User> findByEmail(
            String email) {

        try {

            return dao.findByEmail(email);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public List<User> findAll() {

        try {

            return dao.findAll();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public List<User> search(String keyword) {

        try {

            return dao.search(keyword);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public boolean update(User user) {

        try {

            return dao.update(user);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public boolean delete(int id) {

        try {

            return dao.delete(id);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}