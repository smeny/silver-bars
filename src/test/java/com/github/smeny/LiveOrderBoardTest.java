package com.github.smeny;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LiveOrderBoardTest {

    private LiveOrderBoard orderBoard;

    @BeforeEach
    void setUp() {
        orderBoard = new LiveOrderBoard();
    }

    @Test
    void registerShouldReturnAnOrderID() {
        String orderId = orderBoard.registerOrder("user1", 3.5, 303, OrderType.BUY);

        assertNotNull(orderId, "Registering an order should return a non null order ID");
        assertFalse(orderId.isEmpty(), "Registering an order should return a non empty order ID");
    }

    @Test
    void registerBuyOrderShouldUpdateBoard() {
        String orderId = orderBoard.registerOrder("user1", 3.5, 303, OrderType.BUY);

        List<String> buyOrders = orderBoard.listOrdersForType(OrderType.BUY);
        List<String> sellOrders = orderBoard.listOrdersForType(OrderType.SELL);

        assertFalse(buyOrders.isEmpty());
        assertEquals("3.5 kg for £303", buyOrders.get(0));
        assertTrue(sellOrders.isEmpty());
    }

    @Test
    void registerSellOrderShouldUpdateBoard() {
        String orderId = orderBoard.registerOrder("user1", 3.5, 303, OrderType.SELL);

        List<String> buyOrders = orderBoard.listOrdersForType(OrderType.BUY);
        List<String> sellOrders = orderBoard.listOrdersForType(OrderType.SELL);

        assertTrue(buyOrders.isEmpty());
        assertFalse(sellOrders.isEmpty());
        assertEquals("3.5 kg for £303", sellOrders.get(0));
    }

}