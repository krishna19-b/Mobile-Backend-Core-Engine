package com.mobilebackend.exception;

public class ProductNotFoundException
        extends RuntimeException {

    public ProductNotFoundException(int id) {

        super("Product not found: " + id);
    }
}