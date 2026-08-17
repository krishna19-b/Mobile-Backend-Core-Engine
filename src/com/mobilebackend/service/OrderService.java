package com.mobilebackend.service;

import com.mobilebackend.model.Order;

import java.util.Optional;

public interface OrderService {

    Order create(Order order);

    Optional<Order> findById(int id);

    void cancel(int id);

    java.math.BigDecimal calculateTotal(
            Order order);
}