package com.factoria.veterinary_clinic.controllers;

import com.factoria.veterinary_clinic.models.User;
import com.factoria.veterinary_clinic.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private UserDetails userDetails;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    public void setup() {
        user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");
        user.setName("Test User");
    }

    @Test
    void testCreateUser() {

        User newUser = new User();
        newUser.setEmail("newuser@example.com");
        newUser.setName("New User");
        when(userService.createUser(Mockito.any(User.class))).thenReturn(newUser);

        ResponseEntity<User> response = userController.createUser(newUser);

        assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
        assertThat(response.getBody().getEmail(), is("newuser@example.com"));
    }

    @Test
    void testDeleteUser() {

        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<Void> response = userController.deleteUser(1L);

        assertThat(response.getStatusCode(), is(HttpStatus.NO_CONTENT));
        verify(userService, times(1)).deleteUser(1L);
    }

    @Test
    void testGetAllUsers() {

        when(userService.getAllUsers()).thenReturn(List.of(user));

        List<User> users = userController.getAllUsers();

        assertThat(users, is(not(empty())));
        assertThat(users.size(), is(1));
        assertThat(users.get(0).getName(), is("Test User"));
    }

    @Test
    void testGetUserById() {

        when(userService.getUserById(1L)).thenReturn(java.util.Optional.of(user));
        when(userService.getUserByEmail("user@example.com")).thenReturn(java.util.Optional.of(user));

        when(userDetails.getUsername()).thenReturn("user@example.com");

        ResponseEntity<?> response = userController.getUserById(1L, userDetails);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(((User) response.getBody()).getName(), is("Test User"));
    }

}
