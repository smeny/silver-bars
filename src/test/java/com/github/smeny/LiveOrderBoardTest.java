package com.github.smeny;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LiveOrderBoardTest {

    private LiveOrderBoard orderBoard;

    @BeforeEach
    void setUp() {
        orderBoard = new LiveOrderBoard();
    }

    @Test
    void registerShouldReturnAnOrderID() {
        String orderId = orderBoard.register("user1", 3.5, 303, OrderType.BUY);

        assertNotNull(orderId, "Registering an order should return a non null order ID");
        assertFalse(orderId.isEmpty(), "Registering an order should return a non empty order ID");
    }

}