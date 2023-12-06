package com.example.ecommercepaypal.controllers;

import com.example.ecommercepaypal.models.Order;
import com.example.ecommercepaypal.services.OrderService;
import com.example.ecommercepaypal.services.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    @Autowired
    public OrderController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Order> addOrder(@RequestBody Order order) {
        Order createdOrder = orderService.addOrder(order.getUser().getId(),order.getTotalPrice());
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PostMapping("/{orderId}/checkout")
    public ResponseEntity<String> initiateCheckout(@PathVariable Long orderId) {
        try {
            // Perform any necessary validation or business logic here

            // Call the PaymentService to create a checkout session
            String sessionId = paymentService.createCheckoutSession();

            return new ResponseEntity<>(sessionId, HttpStatus.OK);
        } catch (StripeException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return new ResponseEntity<>("Error initiating checkout", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // Other methods for updating and deleting orders

}
