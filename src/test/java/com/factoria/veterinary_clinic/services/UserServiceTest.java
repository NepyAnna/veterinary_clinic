package com.factoria.veterinary_clinic.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.factoria.veterinary_clinic.models.User;
import com.factoria.veterinary_clinic.repositories.UserRepository;

public class UserServiceTest {
     @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User("John Doe", "password", "john@example.com");
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("encodedPassword", createdUser.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;

        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(
                new User("John Doe", "password1", "john@example.com"),
                new User("Jane Doe", "password2", "jane@example.com")
        );
        when(userRepository.findAll()).thenReturn(users);

        List<User> retrievedUsers = userService.getAllUsers();

        assertNotNull(retrievedUsers);
        assertEquals(2, retrievedUsers.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserByEmail() {
        String email = "john@example.com";
        User user = new User("John Doe", "password", email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserByEmail(email);

        assertTrue(retrievedUser.isPresent());
        assertEquals(email, retrievedUser.get().getEmail());
        verify(userRepository, times(1)).findByEmail(email);
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User user = new User("John Doe", "password", "john@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        Optional<User> retrievedUser = userService.getUserById(userId);

        assertTrue(retrievedUser.isPresent());
        assertEquals("John Doe", retrievedUser.get().getName());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testUpdateUser() {
        Long userId = 1L;
        User existingUser = new User("John Doe", "password", "john@example.com");
        User updatedUser = new User("John Updated", "newPassword", "updated@example.com");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(passwordEncoder.encode(updatedUser.getPassword())).thenReturn("encodedNewPassword");
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        User result = userService.updateUser(userId, updatedUser);

        assertNotNull(result);
        assertEquals("John Updated", result.getName());
        assertEquals("encodedNewPassword", result.getPassword());
        assertEquals("updated@example.com", result.getEmail());
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).save(any(User.class));
    }
}
