package com.portfolio.backend.controller;

import com.portfolio.backend.entity.User;
import com.portfolio.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Add or Register a new User
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Update an existing User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        user.setId(id);  // Here, setId() accepts UUID, so passing a UUID
        return userService.updateUser(user);
    }

    // Delete a User
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    // Get a User by Email (For login or viewing)
    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    // Get all users (for Admin Panel)
    @GetMapping
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
