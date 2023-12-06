package com.example.ecommercepaypal.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<Product> products;
    // You might have a List of OrderItems or other details
    // For simplicity, let's just include a total price here
    private double totalPrice;

    public void calculateTotalPrice() {
        // Implement the logic to calculate total price based on the products
        // This can be summing up the prices of individual products, for example
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice();
        }
        this.totalPrice = total;
    }
}