package com.github.smeny;

import java.util.UUID;

class Order {

    private String orderId;
    private String userId;
    private double quantity;
    private int price;
    private OrderType orderType;

    Order(String userId, double quantity, int price, OrderType orderType) {
        this.orderId = UUID.randomUUID().toString();
        this.userId = userId;
        this.quantity = quantity;
        this.price = price;
        this.orderType = orderType;
    }

    String getOrderId() {
        return orderId;
    }

    double getQuantity() {
        return quantity;
    }

    int getPrice() {
        return price;
    }

    OrderType getOrderType() {
        return orderType;
    }
}
