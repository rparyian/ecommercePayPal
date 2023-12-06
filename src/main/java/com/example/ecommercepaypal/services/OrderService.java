package com.example.ecommercepaypal.services;

import com.example.ecommercepaypal.models.Order;
import com.example.ecommercepaypal.models.User;
import com.example.ecommercepaypal.repos.OrderRepository;
import com.example.ecommercepaypal.repos.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        // Add error handling for non-existent orders
        return orderRepository.findById(id).orElse(null);
    }

    public Order addOrder(Long userId, double totalPrice) {
        // Add business logic/validation as needed
        // Step 1: Create an Order object
        Order order = new Order();

        // Step 2: Associate the User
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        order.setUser(user);

        // Step 3: Set the total price
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    // Other methods for updating and deleting orders

}
