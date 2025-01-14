package com.lcaohoanq.graphql.controllers;

import com.lcaohoanq.graphql.Entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {

    private List<User> users = new ArrayList<>(User.users); // In-memory user list

    // Register a new user
    @MutationMapping
    public User register(@Argument String name, @Argument String email, @Argument String password) {
        int newId = users.size() + 1;
        User newUser = new User(newId, name, email, password);  // Here you can hash the password in a 
        // real case
        users.add(newUser);
        return newUser;
    }

    // User login
    @MutationMapping
    public AuthResponse login(@Argument String email, @Argument String password) {
        Optional<User> foundUser = users.stream()
            .filter(user -> user.email().equals(email) && user.password().equals(password))
            .findFirst();

        if (foundUser.isPresent()) {
            // In a real application, you would verify the password here (e.g., by hashing and comparing)
            return new AuthResponse(true, "Login successful", "fake-jwt-token");
        }

        return new AuthResponse(false, "Invalid credentials", null);
    }

    // Inner class for authentication response
    public record AuthResponse(Boolean success, String message, String token) {}
}
