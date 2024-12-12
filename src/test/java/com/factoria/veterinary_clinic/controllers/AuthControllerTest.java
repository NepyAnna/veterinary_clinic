package com.factoria.veterinary_clinic.controllers;

import com.factoria.veterinary_clinic.dtos.AuthResponseDto;
import com.factoria.veterinary_clinic.dtos.LoginDto;
import com.factoria.veterinary_clinic.services.AuthService;
import com.factoria.veterinary_clinic.services.TokenBlacklistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @InjectMocks
    private AuthController authController;

    @Mock
    private AuthService authService;

    @Mock
    private TokenBlacklistService tokenBlacklistService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login_whenValidCredentials_returnsAuthResponse() {
        LoginDto loginDto = new LoginDto("test@example.com", "password123");
        AuthResponseDto mockResponse = mock(AuthResponseDto.class);

        when(authService.login(loginDto)).thenReturn(mockResponse);

        ResponseEntity<AuthResponseDto> response = authController.login(loginDto);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is(mockResponse));
        verify(authService, times(1)).login(loginDto);
    }

    @Test
    void logout_whenValidToken_logsOutSuccessfully() {
        String authHeader = "Bearer token123";

        doNothing().when(tokenBlacklistService).blacklistToken("token123");

        ResponseEntity<String> response = authController.logout(authHeader);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Logged out successfully!!!"));

        verify(tokenBlacklistService, times(1)).blacklistToken("token123");
    }

    @Test
    void logout_whenNoTokenInHeader_returnsSuccess() {
        String authHeader = null;

        ResponseEntity<String> response = authController.logout(authHeader);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));
        assertThat(response.getBody(), is("Logged out successfully!!!"));

        verify(tokenBlacklistService, never()).blacklistToken(anyString());
    }
}