package com.mobilebackend.dao;

import com.mobilebackend.model.Order;
import com.mobilebackend.model.OrderItem;
import com.mobilebackend.model.OrderStatus;
import com.mobilebackend.model.Product;
import com.mobilebackend.util.DBConnectionUtil;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAO {


    public Order createOrder(
            Order order)
            throws SQLException {

        Connection connection =
                DBConnectionUtil.getConnection();

        try {

            connection.setAutoCommit(false);


            String orderSql =
                    "INSERT INTO orders " +
                            "(user_id,total,status) " +
                            "VALUES (?,?,?) " +
                            "RETURNING id,created_at";


            try (PreparedStatement ps =
                         connection.prepareStatement(
                                 orderSql
                         )) {

                ps.setInt(
                        1,
                        order.getUserId()
                );

                ps.setBigDecimal(
                        2,
                        order.calculateTotal()
                );

                ps.setString(
                        3,
                        order.getStatus().name()
                );

                ResultSet rs =
                        ps.executeQuery();

                if (rs.next()) {

                    order.setId(
                            rs.getInt("id")
                    );

                    order.setTotal(
                            order.calculateTotal()
                    );

                    order.setCreatedAt(
                            rs.getTimestamp(
                                    "created_at"
                            ).toLocalDateTime()
                    );
                }
            }


            String itemSql =
                    "INSERT INTO order_items " +
                            "(order_id,product_id,quantity,price) " +
                            "VALUES (?,?,?,?)";


            String inventorySql =
                    "UPDATE products " +
                            "SET quantity = quantity - ? " +
                            "WHERE id=? AND quantity >= ?";


            for (OrderItem item :
                    order.getItems()) {

                try (
                        PreparedStatement ps =
                                connection.prepareStatement(
                                        itemSql
                                );

                        PreparedStatement inventory =
                                connection.prepareStatement(
                                        inventorySql
                                )
                ) {

                    ps.setInt(
                            1,
                            order.getId()
                    );

                    ps.setInt(
                            2,
                            item.getProductId()
                    );

                    ps.setInt(
                            3,
                            item.getQuantity()
                    );

                    ps.setBigDecimal(
                            4,
                            item.getPrice()
                    );

                    ps.executeUpdate();


                    inventory.setInt(
                            1,
                            item.getQuantity()
                    );

                    inventory.setInt(
                            2,
                            item.getProductId()
                    );

                    inventory.setInt(
                            3,
                            item.getQuantity()
                    );

                    int updated =
                            inventory.executeUpdate();

                    if (updated == 0) {

                        throw new SQLException(
                                "Insufficient inventory"
                        );
                    }
                }
            }

            connection.commit();

            return order;

        } catch (Exception e) {

            connection.rollback();

            throw e;

        } finally {

            connection.close();
        }
    }


    public Optional<Order> findById(
            int id)
            throws SQLException {

        String sql =
                "SELECT * FROM orders WHERE id=?";

        try (
                Connection connection =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if (!rs.next()) {

                return Optional.empty();
            }

            Order order = new Order();

            order.setId(
                    rs.getInt("id")
            );

            order.setUserId(
                    rs.getInt("user_id")
            );

            order.setTotal(
                    rs.getBigDecimal("total")
            );

            order.setStatus(
                    OrderStatus.valueOf(
                            rs.getString("status")
                    )
            );

            order.setCreatedAt(
                    rs.getTimestamp(
                            "created_at"
                    ).toLocalDateTime()
            );

            return Optional.of(order);
        }
    }


    public boolean cancel(int id)
            throws SQLException {

        String sql =
                "UPDATE orders " +
                        "SET status='CANCELLED' " +
                        "WHERE id=?";

        try (
                Connection connection =
                        DBConnectionUtil.getConnection();

                PreparedStatement ps =
                        connection.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;
        }
    }
}