
package com.example.saga.orchestrator;

import com.example.saga.service.InventoryService;
import com.example.saga.service.OrderService;
import com.example.saga.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SagaOrchestrator {

    @Autowired
    private OrderService orderService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    public String executeOrderSaga(String orderId) {
        try {
            // Step 1: Create Order
            orderService.createOrder(orderId);

            // Step 2: Reserve Inventory
            inventoryService.reserveInventory(orderId);

            // Step 3: Process Payment
            paymentService.processPayment(orderId);

            return "Order processed successfully for Order ID: " + orderId;
        } catch (Exception e) {
            // Rollback actions in reverse order
            paymentService.rollbackPayment(orderId);
            inventoryService.rollbackInventory(orderId);
            orderService.rollbackOrder(orderId);

            return "Order failed for Order ID: " + orderId + ". Rollback completed.";
        }
    }
}

