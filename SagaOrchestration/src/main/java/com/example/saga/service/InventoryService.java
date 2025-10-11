package com.example.saga.service;


import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public void reserveInventory(String orderId) {
        System.out.println("Inventory reserved for Order ID: " + orderId);
    }

    public void rollbackInventory(String orderId) {
        System.out.println("Inventory rollback for Order ID: " + orderId);
    }
}

