package com.mobilebackend.repository;

import com.mobilebackend.dao.OrderDAO;
import com.mobilebackend.model.Order;

import java.sql.SQLException;
import java.util.Optional;

public class OrderRepository {

    private final OrderDAO dao;

    public OrderRepository(
            OrderDAO dao) {

        this.dao = dao;
    }

    public Order create(Order order) {

        try {

            return dao.createOrder(order);

        } catch (SQLException e) {

            throw new RuntimeException(
                    "Order creation failed",
                    e
            );
        }
    }

    public Optional<Order> findById(int id) {

        try {

            return dao.findById(id);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public boolean cancel(int id) {

        try {

            return dao.cancel(id);

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }
}