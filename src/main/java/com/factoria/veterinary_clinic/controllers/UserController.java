package com.factoria.veterinary_clinic.controllers;

import com.factoria.veterinary_clinic.models.User;
import com.factoria.veterinary_clinic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        if (!isAdmin(userDetails) && !isCurrentUser(id, userDetails)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("You do not have permission to access this resource.");
        }
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user,
            @AuthenticationPrincipal UserDetails userDetails) {
        if (!isAdmin(userDetails) && !isCurrentUser(id, userDetails)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have permission to update this user.");
        }
        try {
            return ResponseEntity.ok(userService.updateUser(id, user));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isAdmin(UserDetails userDetails) {
        return userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    private boolean isCurrentUser(Long userId, UserDetails userDetails) {
        User currentUser = userService.getUserByEmail(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return currentUser.getId().equals(userId);
    }
}
