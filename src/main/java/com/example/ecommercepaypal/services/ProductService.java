package com.example.ecommercepaypal.services;

import com.example.ecommercepaypal.models.Product;
import com.example.ecommercepaypal.repos.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        // Add error handling for non-existent products
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(Product product) {
        // Add business logic/validation as needed
        return productRepository.save(product);
    }

    // Other methods for updating and deleting products

}
