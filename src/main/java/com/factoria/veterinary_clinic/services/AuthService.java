package com.factoria.veterinary_clinic.services;

import com.factoria.veterinary_clinic.dtos.AuthResponseDto;
import com.factoria.veterinary_clinic.dtos.LoginDto;
import com.factoria.veterinary_clinic.dtos.UserDto;
import com.factoria.veterinary_clinic.enums.Role;
import com.factoria.veterinary_clinic.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(
            UserService userService,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponseDto register(UserDto userDto) {
        User user = new User(
                userDto.name(),
                passwordEncoder.encode(userDto.password()),
                userDto.email());
        user.setRole(Role.USER);
        userService.createUser(user);
        String token = jwtService.generateToken(user.getName());
        return new AuthResponseDto(token);
    }

    public AuthResponseDto login(LoginDto loginDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.email(),
                        loginDto.password()));

        User user = userService.getUserByEmail(loginDto.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtService.generateToken(user.getEmail());
        return new AuthResponseDto(token);
    }
}
