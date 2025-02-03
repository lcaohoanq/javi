// UserController.java
package com.lcaohoanq.demo.domain.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "Operations related to User")
@Slf4j
@RestController
@RequestMapping("${API_PREFIX}/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    
    @Operation(
        summary = "Get all users",
        description = "Retrieves a list of all users",
        responses = {
            @ApiResponse(responseCode = "200", description = "List of users")
        }
    )
    @GetMapping("")
    public ResponseEntity<?> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.ok(userRepository.findAll(PageRequest.of(page, size)));
    }
    
    @Operation(
        summary = "Get user by ID",
        description = "Retrieves a user by ID",
        responses = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }
    
    @Operation(
        summary = "Register new user",
        description = "Creates a new user account",
        responses = {
            @ApiResponse(responseCode = "200", description = "User successfully created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
        }
    )
    @PostMapping("/register")
    public ResponseEntity<com.lcaohoanq.demo.api.ApiResponse<User>> createUser(
        @Valid @RequestBody UserDTO userDTO) {
        User createdUser = userService.create(userDTO);
        
        return ResponseEntity.ok().body(
            com.lcaohoanq.demo.api.ApiResponse.<User>builder()
                .message("User successfully created")
                .data(createdUser)
                .build()
        );
    }

    @Operation(
        summary = "Update user",
        description = "Updates an existing user",
        responses = {
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
        }
    )
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
        @PathVariable Long id,
        @Valid @RequestBody UserDTO userDTO) {
        User updatedUser = userService.update(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(
        summary = "Delete user",
        description = "Deletes an existing user",
        responses = {
            @ApiResponse(responseCode = "200", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
        }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}