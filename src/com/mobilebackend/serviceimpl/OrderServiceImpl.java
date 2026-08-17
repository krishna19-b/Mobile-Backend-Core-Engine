package com.mobilebackend.serviceimpl;

import com.mobilebackend.exception.OrderNotFoundException;
import com.mobilebackend.model.Order;
import com.mobilebackend.repository.OrderRepository;
import com.mobilebackend.service.OrderService;

import java.math.BigDecimal;
import java.util.Optional;

public class OrderServiceImpl
        implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(
            OrderRepository repository) {

        this.repository = repository;
    }


    @Override
    public Order create(Order order) {

        if (order == null) {

            throw new IllegalArgumentException(
                    "Order cannot be null"
            );
        }

        if (order.getItems().isEmpty()) {

            throw new IllegalArgumentException(
                    "Order must contain items"
            );
        }

        order.setTotal(
                calculateTotal(order)
        );

        return repository.create(order);
    }


    @Override
    public Optional<Order> findById(int id) {

        return repository.findById(id);
    }


    @Override
    public void cancel(int id) {

        if (repository.findById(id).isEmpty()) {

            throw new OrderNotFoundException(id);
        }

        repository.cancel(id);
    }


    @Override
    public BigDecimal calculateTotal(
            Order order) {

        return order.getItems()
                .stream()
                .map(item ->
                        item.getPrice()
                                .multiply(
                                        BigDecimal.valueOf(
                                                item.getQuantity()
                                        )
                                )
                )
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );
    }
}