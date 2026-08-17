package com.mobilebackend.dao;

import com.mobilebackend.model.Product;
import com.mobilebackend.util.DBConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO {

    public Product save(Product product)
            throws SQLException {

        String sql =
                "INSERT INTO products " +
                        "(name,category,price,quantity) " +
                        "VALUES (?,?,?,?) " +
                        "RETURNING id,created_at";

        try (
                Connection con =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setBigDecimal(3, product.getPrice());
            ps.setInt(4, product.getQuantity());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                product.setId(
                        rs.getInt("id")
                );

                product.setCreatedAt(
                        rs.getTimestamp(
                                "created_at"
                        ).toLocalDateTime()
                );
            }

            return product;
        }
    }


    public Optional<Product> findById(int id)
            throws SQLException {

        String sql =
                "SELECT * FROM products WHERE id=?";

        try (
                Connection con =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                return Optional.of(
                        mapProduct(rs)
                );
            }

            return Optional.empty();
        }
    }


    public List<Product> findAll()
            throws SQLException {

        String sql =
                "SELECT * FROM products";

        List<Product> products =
                new ArrayList<>();

        try (
                Connection con =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                products.add(
                        mapProduct(rs)
                );
            }
        }

        return products;
    }


    public List<Product> search(
            String keyword)
            throws SQLException {

        String sql =
                "SELECT * FROM products " +
                        "WHERE LOWER(name) LIKE ?";

        List<Product> products =
                new ArrayList<>();

        try (
                Connection con =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    "%" + keyword.toLowerCase() + "%"
            );

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                products.add(
                        mapProduct(rs)
                );
            }
        }

        return products;
    }


    public List<Product> findByCategory(
            String category)
            throws SQLException {

        String sql =
                "SELECT * FROM products " +
                        "WHERE LOWER(category)=LOWER(?)";

        List<Product> products =
                new ArrayList<>();

        try (
                Connection con =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, category);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                products.add(
                        mapProduct(rs)
                );
            }
        }

        return products;
    }


    public boolean update(Product product)
            throws SQLException {

        String sql =
                "UPDATE products " +
                        "SET name=?,category=?,price=?,quantity=? " +
                        "WHERE id=?";

        try (
                Connection con =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, product.getName());
            ps.setString(2, product.getCategory());
            ps.setBigDecimal(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getId());

            return ps.executeUpdate() > 0;
        }
    }


    public boolean delete(int id)
            throws SQLException {

        String sql =
                "DELETE FROM products WHERE id=?";

        try (
                Connection con =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }


    private Product mapProduct(
            ResultSet rs)
            throws SQLException {

        Product product = new Product();

        product.setId(
                rs.getInt("id")
        );

        product.setName(
                rs.getString("name")
        );

        product.setCategory(
                rs.getString("category")
        );

        product.setPrice(
                rs.getBigDecimal("price")
        );

        product.setQuantity(
                rs.getInt("quantity")
        );

        Timestamp timestamp =
                rs.getTimestamp("created_at");

        if (timestamp != null) {

            product.setCreatedAt(
                    timestamp.toLocalDateTime()
            );
        }

        return product;
    }
}