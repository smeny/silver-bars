package com.github.smeny;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

class LiveOrderBoard {

    private List<Order> orders = new ArrayList<>();

    public String registerOrder(String userId, double quantity, int price, OrderType orderType) {
        Order order = new Order(userId, quantity, price, orderType);
        orders.add(order);
        return order.getOrderId();
    }

    public List<String> summaryForOrderType(OrderType orderType) {
        Map<Integer, Double> quantityByPrice = getTotalQuantityByPrice(orderType);
        return getSortedSummary(orderType, quantityByPrice);
    }

    public void deleteOrder(String orderId) {
        orders.removeIf(order -> order.getOrderId().equals(orderId));
    }

    private String displayQuantityPerPrice(double quantity, int price) {
        //  If the precision is less than the number of digits which would appear after the decimal point in the string returned by Float.toString(float)
        //  or Double.toString(double) respectively, then the value will be rounded using the round half up algorithm.
        return String.format(Locale.UK, "%.1f kg for £%d", quantity, price);
    }

    private Map<Integer, Double> getTotalQuantityByPrice(OrderType orderType) {
        return orders.stream()
                .filter(order -> order.getOrderType().equals(orderType))
                .collect(Collectors.groupingBy(Order::getPrice, reducing(
                        0.0, Order::getQuantity, Double::sum
                )));
    }

    private List<String> getSortedSummary(OrderType orderType, Map<Integer, Double> quantityByPrice) {
        return quantityByPrice.entrySet().stream()
                .sorted(compareForOrderType(orderType))
                .map(entry -> displayQuantityPerPrice(entry.getValue(), entry.getKey()))
                .collect(toList());
    }

    private Comparator<Map.Entry<Integer, Double>> compareForOrderType(OrderType orderType) {
        return Comparator.comparing(Map.Entry::getKey);
    }

}
