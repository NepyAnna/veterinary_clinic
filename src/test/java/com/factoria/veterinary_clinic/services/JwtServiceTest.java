package com.factoria.veterinary_clinic.services;

import com.factoria.veterinary_clinic.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
    }

    @Test
    void testGenerateToken() {
        String email = "test@example.com";
        Role role = Role.USER;

        String token = jwtService.generateToken(email, role);

        assertThat(token, is(notNullValue()));
        assertThat(token, is(not(emptyString())));
    }

    @Test
    void testExtractUsername() {
        String email = "test@example.com";
        String token = jwtService.generateToken(email, Role.USER);

        String extractedEmail = jwtService.extractUsername(token);

        assertThat(extractedEmail, is(equalTo(email)));
    }

    @Test
    void testIsTokenValid() {
        String email = "test@example.com";
        Role role = Role.ADMIN;
        String token = jwtService.generateToken(email, role);

        boolean isValid = jwtService.isTokenValid(token, email);

        assertThat(isValid, is(true));
    }

    @Test
    void testExtractClaim() {
        String email = "test@example.com";
        Role role = Role.USER;
        String token = jwtService.generateToken(email, role);

        String extractedEmail = jwtService.extractClaim(token, claims -> claims.get("email", String.class));

        assertThat(extractedEmail, is(equalTo(email)));
    }

    @Test
    void testIsTokenValid_invalidToken() {
        String email = "test@example.com";
        Role role = Role.USER;
        String token = jwtService.generateToken(email, role);

        String wrongEmail = "wrong@example.com";
        boolean isValid = jwtService.isTokenValid(token, wrongEmail);

        assertThat(isValid, is(false));
    }

}
