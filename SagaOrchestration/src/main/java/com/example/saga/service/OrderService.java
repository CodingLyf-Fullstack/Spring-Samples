package com.example.saga.service;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void createOrder(String orderId) {
        System.out.println("Order created for Order ID: " + orderId);
    }

    public void rollbackOrder(String orderId) {
        System.out.println("Order rollback for Order ID: " + orderId);
    }
}
