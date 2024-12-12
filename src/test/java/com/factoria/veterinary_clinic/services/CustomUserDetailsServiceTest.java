package com.factoria.veterinary_clinic.services;

import com.factoria.veterinary_clinic.models.User;
import com.factoria.veterinary_clinic.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;

    private User mockUser;

    @BeforeEach
    void setUp() {
        mockUser = new User("John Doe", "password123", "john.doe@example.com");
        mockUser.setRole(com.factoria.veterinary_clinic.enums.Role.USER);
    }

    @Test
    void testLoadUserByUsername_ShouldReturnUserDetails_WhenUserExists() {
        String email = "john.doe@example.com";
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(mockUser));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

        assertThat(userDetails, is(notNullValue()));
        assertThat(userDetails.getUsername(), is(equalTo(mockUser.getEmail())));
        assertThat(userDetails.getPassword(), is(equalTo(mockUser.getPassword())));
        assertThat(userDetails.getAuthorities(), hasSize(1));
        assertThat(userDetails.getAuthorities().iterator().next().getAuthority(), is(equalTo("ROLE_USER")));
    }

    @Test
    void testLoadUserByUsername_ShouldThrowUsernameNotFoundException_WhenUserDoesNotExist() {
        String email = "non.existent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.empty());

        try {
            customUserDetailsService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            assertThat(e.getMessage(), is(equalTo("User not found")));
        }
    }
}
