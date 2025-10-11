
package com.example.saga.controller;

import com.example.saga.orchestrator.SagaOrchestrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private SagaOrchestrator sagaOrchestrator;

    @PostMapping("/create")
    public String createOrder(@RequestParam String orderId) {
        return sagaOrchestrator.executeOrderSaga(orderId);
    }
}
