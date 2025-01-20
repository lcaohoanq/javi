package com.lcaohoanq.rxjava;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers()
            .toList()
            .blockingGet();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id)
            .blockingGet();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/processed")
    public ResponseEntity<List<String>> getProcessedUsers() {
        List<String> processedData = userService.processUserData()
            .toList()
            .blockingGet();
        return ResponseEntity.ok(processedData);
    }

    @GetMapping("/combined")
    public ResponseEntity<List<String>> getCombinedData() {
        List<String> combinedData = userService.getCombinedUserData()
            .toList()
            .blockingGet();
        return ResponseEntity.ok(combinedData);
    }
    
}
