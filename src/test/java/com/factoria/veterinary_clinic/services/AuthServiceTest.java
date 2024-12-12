package com.factoria.veterinary_clinic.services;

import com.factoria.veterinary_clinic.dtos.AuthResponseDto;
import com.factoria.veterinary_clinic.dtos.LoginDto;
import com.factoria.veterinary_clinic.dtos.UserDto;
import com.factoria.veterinary_clinic.models.User;
import com.factoria.veterinary_clinic.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;

public class AuthServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    private UserDto userDto;
    private LoginDto loginDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        userDto = new UserDto("testUser", "password", "testuser@example.com");
        loginDto = new LoginDto("testuser@example.com", "password");
    }

    @Test
    public void shouldRegisterUserAndGenerateToken() {
        User user = new User("testUser", "password", "testuser@example.com");
        user.setRole(Role.USER);
        when(userService.createUser(any(User.class))).thenReturn(user);

        when(jwtService.generateToken(user.getEmail(), user.getRole())).thenReturn("mockedToken");

        AuthResponseDto authResponse = authService.register(userDto);

        verify(userService).createUser(any(User.class));

        assertThat(authResponse, is(notNullValue()));

        assertThat(authResponse.toString(), containsString("mockedToken"));

        assertThat(authResponse.toString(), containsString("USER"));
    }

    @Test
    public void shouldLoginUserAndGenerateToken() {

        User user = new User("testUser", "password", "testuser@example.com");
        user.setRole(Role.USER);
        when(userService.getUserByEmail(loginDto.email())).thenReturn(java.util.Optional.of(user));

        when(jwtService.generateToken(user.getEmail(), user.getRole())).thenReturn("mockedToken");

        AuthResponseDto authResponse = authService.login(loginDto);

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

        assertThat(authResponse, is(notNullValue()));
        assertThat(authResponse.toString(), containsString("mockedToken"));
        assertThat(authResponse.toString(), containsString("USER"));
    }

    @Test
    public void shouldThrowExceptionWhenUserNotFoundDuringLogin() {

        when(userService.getUserByEmail(loginDto.email())).thenReturn(java.util.Optional.empty());

        try {
            authService.login(loginDto);
        } catch (RuntimeException e) {
            assertThat(e.getMessage(), is("User not found"));
        }
    }
}
