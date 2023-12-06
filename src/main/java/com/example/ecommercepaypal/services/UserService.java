package com.example.ecommercepaypal.services;

import com.example.ecommercepaypal.models.User;
import com.example.ecommercepaypal.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        // Add error handling for non-existent users
        return userRepository.findById(id).orElse(null);
    }

    public User addUser(User user) {
        // Add business logic/validation as needed
        return userRepository.save(user);
    }

    // Other methods for updating and deleting users

}
