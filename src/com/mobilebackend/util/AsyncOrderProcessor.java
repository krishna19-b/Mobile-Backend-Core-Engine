package com.mobilebackend.util;

import com.mobilebackend.model.Order;
import com.mobilebackend.service.OrderService;

import java.util.concurrent.*;

public class AsyncOrderProcessor {

    private final ExecutorService executor =
            Executors.newFixedThreadPool(3);

    private final OrderService orderService;

    public AsyncOrderProcessor(
            OrderService orderService) {

        this.orderService = orderService;
    }


    public Future<Order> process(
            Order order) {

        Callable<Order> task =
                () -> {

                    System.out.println(
                            "Processing order on: "
                                    + Thread.currentThread()
                                    .getName()
                    );

                    return orderService.create(order);
                };

        return executor.submit(task);
    }


    public void shutdown() {

        executor.shutdown();
    }
}