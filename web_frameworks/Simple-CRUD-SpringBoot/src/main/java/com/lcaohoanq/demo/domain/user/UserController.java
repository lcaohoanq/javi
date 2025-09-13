package com.lcaohoanq.demo.domain.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${API_PREFIX}/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserRepository userRepository;
    
    @GetMapping("")
    public ResponseEntity<?> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size)
    {
        return ResponseEntity.ok(userRepository.findAll(PageRequest.of(page, size)));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userServiceImpl.findById(id);
        return ResponseEntity.ok(user);
    }
    
    @PostMapping("/register")
    public ResponseEntity<User> createUser(
        @Valid @RequestBody UserDTO userDTO) {
        User createdUser = userServiceImpl.create(userDTO);
        return ResponseEntity.ok().body(createdUser);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(
        @PathVariable Long id,
        @Valid @RequestBody UserDTO userDTO) {
        User updatedUser = userServiceImpl.update(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServiceImpl.delete(id);
        return ResponseEntity.noContent().build();
    }
}