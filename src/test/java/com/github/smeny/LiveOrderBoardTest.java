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

        assertFalse(buyOrders.isEmpty(), "BUY orders should contain a new registered order");
        assertTrue(sellOrders.isEmpty(), "Registering a BUY order should not affect SELL orders");
        assertEquals("3.5 kg for £303", buyOrders.get(0));
    }

    @Test
    void registerSellOrderShouldUpdateBoard() {
        String orderId = orderBoard.registerOrder("user1", 3.5, 303, OrderType.SELL);

        List<String> buyOrders = orderBoard.listOrdersForType(OrderType.BUY);
        List<String> sellOrders = orderBoard.listOrdersForType(OrderType.SELL);

        assertTrue(buyOrders.isEmpty(), "Registering a SELL order should not affect BUY orders");
        assertFalse(sellOrders.isEmpty(), "SELL orders should contain a new registered order");
        assertEquals("3.5 kg for £303", sellOrders.get(0));
    }

    @Test
    void deleteShouldRemoveOrder() {
        String orderId1 = orderBoard.registerOrder("user1", 3.5, 303, OrderType.BUY);
        String orderId2 = orderBoard.registerOrder("user2", 5.5, 306, OrderType.BUY);
        String orderId3 = orderBoard.registerOrder("user3", 4, 310, OrderType.SELL);

        orderBoard.deleteOrder(orderId1);

        List<String> buyOrders = orderBoard.listOrdersForType(OrderType.BUY);
        List<String> sellOrders = orderBoard.listOrdersForType(OrderType.SELL);
        assertEquals(1, buyOrders.size(), "Deleting one BUY order should bring the count down by 1");
        assertEquals(1, sellOrders.size(), "Deleting a BUY order should not affect SELL orders");
        assertEquals("5.5 kg for £306", buyOrders.get(0));
    }

    @Test
    void ordersForSamePriceShouldBeMerged() {

    }

}