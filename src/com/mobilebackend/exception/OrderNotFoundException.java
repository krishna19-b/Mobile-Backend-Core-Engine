package com.mobilebackend.exception;

public class OrderNotFoundException
        extends RuntimeException {

    public OrderNotFoundException(int id) {

        super("Order not found: " + id);
    }
}