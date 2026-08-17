package com.mobilebackend;

import com.mobilebackend.controller.OrderController;
import com.mobilebackend.controller.ProductController;
import com.mobilebackend.controller.UserController;

import com.mobilebackend.dao.OrderDAO;
import com.mobilebackend.dao.ProductDAO;
import com.mobilebackend.dao.UserDAO;

import com.mobilebackend.model.Order;
import com.mobilebackend.model.OrderItem;
import com.mobilebackend.model.Product;
import com.mobilebackend.model.User;

import com.mobilebackend.repository.OrderRepository;
import com.mobilebackend.repository.ProductRepository;
import com.mobilebackend.repository.UserRepository;

import com.mobilebackend.service.AuthenticationService;
import com.mobilebackend.service.OrderService;
import com.mobilebackend.service.ProductService;
import com.mobilebackend.service.UserService;

import com.mobilebackend.serviceimpl.AuthenticationServiceImpl;
import com.mobilebackend.serviceimpl.OrderServiceImpl;
import com.mobilebackend.serviceimpl.ProductServiceImpl;
import com.mobilebackend.serviceimpl.UserServiceImpl;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {


       //User ser

        UserDAO userDAO =
                new UserDAO();

        UserRepository userRepository =
                new UserRepository(userDAO);

        UserService userService =
                new UserServiceImpl(
                        userRepository
                );

        UserController userController =
                new UserController(
                        userService
                );

         //PRODUCT SETUP

        ProductDAO productDAO =
                new ProductDAO();

        ProductRepository productRepository =
                new ProductRepository(
                        productDAO
                );

        ProductService productService =
                new ProductServiceImpl(
                        productRepository
                );

        ProductController productController =
                new ProductController(
                        productService
                );

        // ORDER SETUP

        OrderDAO orderDAO =
                new OrderDAO();

        OrderRepository orderRepository =
                new OrderRepository(
                        orderDAO
                );

        OrderService orderService =
                new OrderServiceImpl(
                        orderRepository
                );

        OrderController orderController =
                new OrderController(
                        orderService
                );

         // REGISTER USER

        System.out.println(
                "\n--- REGISTER USER ---"
        );

        try {

            User user =
                    userController.register(
                            "Krishna",
                            "krishna@gmail.com",
                            "123456",
                            "9999999999"
                    );

            System.out.println(user);

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }

         // FIND USER

        System.out.println(
                "\n--- FIND USER ---"
        );

        userController.findUser(1);



         //SEARCH USER

        System.out.println(
                "\n--- SEARCH USERS ---"
        );

        userController.searchUsers(
                "Krishna"
        );

         // UPDATE USER

        System.out.println(
                "\n--- UPDATE USER ---"
        );

        try {

            User user =
                    new User(
                            1,
                            "Krishna Updated",
                            "krishna@gmail.com",
                            "654321",
                            "8888888888"
                    );

            userController.updateUser(user);

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }

         // PRODUCT

        System.out.println(
                "\n--- ADD PRODUCT ---"
        );

        Product laptop =
                new Product(
                        "Gaming Laptop",
                        "Electronics",
                        new BigDecimal("75000"),
                        10
                );

        Product savedProduct =
                productController.add(laptop);

        System.out.println(savedProduct);

         // SEARCH PRODUCT

        System.out.println(
                "\n--- SEARCH PRODUCT ---"
        );

        productController.search(
                "Laptop"
        );

         // FILTER PRODUCT

        System.out.println(
                "\n--- FILTER PRODUCT ---"
        );

        productController.filter(
                "Electronics"
        );

         // SORT PRODUCT

        System.out.println(
                "\n--- SORT PRODUCT ---"
        );

        productController.sort(true);

         // LOGIN

        System.out.println(
                "\n--- LOGIN ---"
        );

        AuthenticationService authService =
                new AuthenticationServiceImpl(
                        userRepository
                );

        try {

            User loggedIn =
                    authService.login(
                            "bala@gmail.com",
                            "123456"
                    );

            System.out.println(
                    "Login successful: "
                            + loggedIn.getName()
            );

        } catch (Exception e) {

            System.out.println(
                    e.getMessage()
            );
        }



         // CREATE ORDER

        System.out.println(
                "\n--- CREATE ORDER ---"
        );

        try {

            Order order =
                    new Order(1);


            OrderItem item1 =
                    new OrderItem(
                            1,
                            2,
                            new BigDecimal("55000")
                    );


            OrderItem item2 =
                    new OrderItem(
                            3,
                            1,
                            new BigDecimal("1500")
                    );


            order.addItem(item1);

            order.addItem(item2);


            Order savedOrder =
                    orderController.create(
                            order
                    );


            System.out.println(
                    savedOrder
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

         // FIND ORDER


        System.out.println(
                "\n--- FIND ORDER ---"
        );

        orderController.find(1);

        System.out.println(
                "\nApplication completed."
        );
    }
}