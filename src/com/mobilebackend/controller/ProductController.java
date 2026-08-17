package com.mobilebackend.controller;

import com.mobilebackend.model.Product;
import com.mobilebackend.service.ProductService;

import java.util.List;

public class ProductController {

    private final ProductService service;

    public ProductController(
            ProductService service) {

        this.service = service;
    }


    public Product add(Product product) {

        return service.add(product);
    }


    public void find(int id) {

        service.findById(id)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println(
                                "Product not found"
                        )
                );
    }


    public void search(String keyword) {

        service.search(keyword)
                .forEach(System.out::println);
    }


    public void filter(String category) {

        service.filterByCategory(category)
                .forEach(System.out::println);
    }


    public void sort(boolean ascending) {

        service.sortByPrice(ascending)
                .forEach(System.out::println);
    }


    public void delete(int id) {

        service.delete(id);

        System.out.println(
                "Product deleted"
        );
    }
}