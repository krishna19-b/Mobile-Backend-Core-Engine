package com.mobilebackend.repository;

import com.mobilebackend.dao.ProductDAO;
import com.mobilebackend.model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProductRepository {

    private final ProductDAO dao;

    public ProductRepository(
            ProductDAO dao) {

        this.dao = dao;
    }

    public Product save(Product product) {

        try {
            return dao.save(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Product> findById(int id) {

        try {
            return dao.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> findAll() {

        try {
            return dao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> search(String keyword) {

        try {
            return dao.search(keyword);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> findByCategory(
            String category) {

        try {
            return dao.findByCategory(category);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean update(Product product) {

        try {
            return dao.update(product);
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