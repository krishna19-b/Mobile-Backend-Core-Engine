package com.mobilebackend.dao;

import com.mobilebackend.model.User;
import com.mobilebackend.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAO {

    public User save(User user)
            throws SQLException {

        String sql =
                "INSERT INTO users " +
                        "(name,email,password,phone) " +
                        "VALUES (?,?,?,?) " +
                        "RETURNING id,created_at";

        try (
                Connection connection =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user.setId(rs.getInt("id"));

                user.setCreatedAt(
                        rs.getTimestamp(
                                "created_at"
                        ).toLocalDateTime()
                );
            }

            return user;
        }
    }


    public Optional<User> findById(int id)
            throws SQLException {

        String sql =
                "SELECT * FROM users WHERE id = ?";

        try (
                Connection connection =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return Optional.of(mapUser(rs));
            }

            return Optional.empty();
        }
    }


    public Optional<User> findByEmail(
            String email)
            throws SQLException {

        String sql =
                "SELECT * FROM users WHERE email = ?";

        try (
                Connection connection =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return Optional.of(mapUser(rs));
            }

            return Optional.empty();
        }
    }


    public List<User> findAll()
            throws SQLException {

        String sql =
                "SELECT * FROM users ORDER BY id";

        List<User> users =
                new ArrayList<>();

        try (
                Connection connection =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                users.add(mapUser(rs));
            }
        }

        return users;
    }


    public List<User> search(
            String keyword)
            throws SQLException {

        String sql =
                "SELECT * FROM users " +
                        "WHERE LOWER(name) LIKE ? " +
                        "OR LOWER(email) LIKE ? " +
                        "ORDER BY name";

        List<User> users =
                new ArrayList<>();

        try (
                Connection connection =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            String value =
                    "%" + keyword.toLowerCase() + "%";

            ps.setString(1, value);
            ps.setString(2, value);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                users.add(mapUser(rs));
            }
        }

        return users;
    }


    public boolean update(User user)
            throws SQLException {

        String sql =
                "UPDATE users " +
                        "SET name=?, email=?, password=?, phone=? " +
                        "WHERE id=?";

        try (
                Connection connection =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setInt(5, user.getId());

            return ps.executeUpdate() > 0;
        }
    }


    public boolean delete(int id)
            throws SQLException {

        String sql =
                "DELETE FROM users WHERE id=?";

        try (
                Connection connection =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }


    private User mapUser(ResultSet rs)
            throws SQLException {

        User user = new User();

        user.setId(
                rs.getInt("id")
        );

        user.setName(
                rs.getString("name")
        );

        user.setEmail(
                rs.getString("email")
        );

        user.setPassword(
                rs.getString("password")
        );

        user.setPhone(
                rs.getString("phone")
        );

        Timestamp timestamp =
                rs.getTimestamp("created_at");

        if (timestamp != null) {

            user.setCreatedAt(
                    timestamp.toLocalDateTime()
            );
        }

        return user;
    }
}