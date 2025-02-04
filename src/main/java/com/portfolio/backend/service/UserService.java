package com.portfolio.backend.service;

import com.portfolio.backend.entity.User;
import com.portfolio.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register a new user
    public String registerUser(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            return "User already exists";
        }

        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashedPassword);
        userRepository.save(user);

        return "User registered successfully";
    }

    // Authenticate a user
    public boolean authenticateUser(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent() && passwordEncoder.matches(password, user.get().getPassword());
    }

    // Delete a user by UUID and return boolean for success
    public boolean deleteUser(UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true; // Success
        }
        return false; // User not found
    }

    // Save a new user or update an existing one
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Update an existing user (return the updated user)
    public boolean updateUser(UUID userId, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(userId);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // Update the fields of the existing user with the values from the updated user
            user.setEmail(updatedUser.getEmail());
            user.setPassword(updatedUser.getPassword());  // You might want to hash the password here too
            userRepository.save(user);  // Save and return the updated user
            return true;  // Update successful
        }
        return false;  // User not found
    }

    // Get a user by email
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Get all users as a List (convert Iterable to List)
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); // Convert Iterable to List
        return users;
    }
}
