package com.mobilebackend.controller;

import com.mobilebackend.model.Order;
import com.mobilebackend.service.OrderService;

public class OrderController {

    private final OrderService service;

    public OrderController(
            OrderService service) {

        this.service = service;
    }


    public Order create(Order order) {

        return service.create(order);
    }


    public void find(int id) {

        service.findById(id)
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println(
                                "Order not found"
                        )
                );
    }


    public void cancel(int id) {

        service.cancel(id);

        System.out.println(
                "Order cancelled"
        );
    }
}