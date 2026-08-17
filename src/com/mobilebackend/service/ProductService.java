package com.mobilebackend.service;

import com.mobilebackend.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product add(Product product);

    Optional<Product> findById(int id);

    Product update(Product product);

    void delete(int id);

    List<Product> search(String keyword);

    List<Product> filterByCategory(
            String category);

    List<Product> sortByPrice(
            boolean ascending);
}