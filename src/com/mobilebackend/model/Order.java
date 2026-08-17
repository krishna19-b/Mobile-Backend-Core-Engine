package com.mobilebackend.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int id;
    private int userId;
    private BigDecimal total;
    private OrderStatus status;
    private LocalDateTime createdAt;

    private List<OrderItem> items =
            new ArrayList<>();

    public Order() {
    }

    public Order(int userId) {

        this.userId = userId;
        this.status = OrderStatus.CREATED;
        this.total = BigDecimal.ZERO;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void addItem(OrderItem item) {

        items.add(item);
    }

    public BigDecimal calculateTotal() {

        return items.stream()
                .map(OrderItem::getTotal)
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );
    }

    @Override
    public String toString() {

        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", total=" + total +
                ", status=" + status +
                ", items=" + items.size() +
                '}';
    }
}