package com.example.saga.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void processPayment(String orderId) {
        System.out.println("Payment processed for Order ID: " + orderId);

        // Simulate failure for testing rollback
        if (orderId.equals("fail")) {
            throw new RuntimeException("Payment failed for Order ID: " + orderId);
        }
    }

    public void rollbackPayment(String orderId) {
        System.out.println("Payment rollback for Order ID: " + orderId);
    }
}
