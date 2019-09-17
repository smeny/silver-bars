package com.github.smeny;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

class LiveOrderBoard {

    private List<Order> orders = new ArrayList<>();

    public String registerOrder(String userId, double quantity, int price, OrderType orderType) {
        Order order = new Order(userId, quantity, price, orderType);
        orders.add(order);
        return order.getOrderId();
    }

    public List<String> listOrdersForType(OrderType orderType) {
        return orders.stream()
                .filter(order -> order.getOrderType().equals(orderType))
                .map(this::displayOrder)
                .collect(Collectors.toList());
    }

    public void deleteOrder(String orderId) {
        orders.removeIf(order -> order.getOrderId().equals(orderId));
    }

    private String displayOrder(Order order) {
        //  If the precision is less than the number of digits which would appear after the decimal point in the string returned by Float.toString(float)
        //  or Double.toString(double) respectively, then the value will be rounded using the round half up algorithm.
        return String.format(Locale.UK, "%.1f kg for £%d", order.getQuantity(), order.getPrice());
    }
}
