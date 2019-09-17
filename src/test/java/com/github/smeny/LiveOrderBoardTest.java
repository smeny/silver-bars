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

        List<String> buySummary = orderBoard.summaryForOrderType(OrderType.BUY);
        List<String> sellSummary = orderBoard.summaryForOrderType(OrderType.SELL);

        assertFalse(buySummary.isEmpty(), "BUY orders should contain a new registered order");
        assertTrue(sellSummary.isEmpty(), "Registering a BUY order should not affect SELL orders");
        assertEquals("3.5 kg for £303", buySummary.get(0));
    }

    @Test
    void registerSellOrderShouldUpdateBoard() {
        String orderId = orderBoard.registerOrder("user1", 3.5, 303, OrderType.SELL);

        List<String> buySummary = orderBoard.summaryForOrderType(OrderType.BUY);
        List<String> sellSummary = orderBoard.summaryForOrderType(OrderType.SELL);

        assertTrue(buySummary.isEmpty(), "Registering a SELL order should not affect BUY orders");
        assertFalse(sellSummary.isEmpty(), "SELL orders should contain a new registered order");
        assertEquals("3.5 kg for £303", sellSummary.get(0));
    }

    @Test
    void deleteShouldRemoveOrder() {
        String orderId1 = orderBoard.registerOrder("user1", 3.5, 303, OrderType.BUY);
        String orderId2 = orderBoard.registerOrder("user2", 5.5, 306, OrderType.BUY);
        String orderId3 = orderBoard.registerOrder("user3", 4, 310, OrderType.SELL);

        orderBoard.deleteOrder(orderId1);

        List<String> buySummary = orderBoard.summaryForOrderType(OrderType.BUY);
        List<String> sellSummary = orderBoard.summaryForOrderType(OrderType.SELL);
        assertEquals(1, buySummary.size(), "Deleting one BUY order should bring the count down by 1");
        assertEquals(1, sellSummary.size(), "Deleting a BUY order should not affect SELL orders");
        assertEquals("5.5 kg for £306", buySummary.get(0));
    }

    @Test
    void ordersForSamePriceShouldBeMerged() {
        orderBoard.registerOrder("user1", 3.5, 306, OrderType.SELL);
        orderBoard.registerOrder("user2", 2.0, 306, OrderType.SELL);

        List<String> sellSummary = orderBoard.summaryForOrderType(OrderType.SELL);

        assertEquals(1, sellSummary.size(), "Two orders with the same price should be merged into one");
        assertEquals("5.5 kg for £306", sellSummary.get(0));
    }

}