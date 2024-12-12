package com.factoria.veterinary_clinic.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class TokenBlacklistServiceTest {

    private TokenBlacklistService tokenBlacklistService;

    @BeforeEach
    void setUp() {
        tokenBlacklistService = new TokenBlacklistService();
    }

    @Test
    void testBlacklistToken_ShouldAddTokenToBlacklist() {
        String token = "dummyToken123";

        tokenBlacklistService.blacklistToken(token);

        assertThat(tokenBlacklistService.isTokenBlacklisted(token), is(true));
    }

    @Test
    void testIsTokenBlacklisted_ShouldReturnTrueForBlacklistedToken() {
        String token = "blacklistedToken";

        tokenBlacklistService.blacklistToken(token);

        assertThat(tokenBlacklistService.isTokenBlacklisted(token), is(true));
    }

    @Test
    void testIsTokenBlacklisted_ShouldReturnFalseForNonBlacklistedToken() {
        String token = "nonBlacklistedToken";

        assertThat(tokenBlacklistService.isTokenBlacklisted(token), is(false));
    }

    @Test
    void testBlacklistToken_ShouldNotAffectOtherTokens() {
        String token1 = "token1";
        String token2 = "token2";

        tokenBlacklistService.blacklistToken(token1);

        assertThat(tokenBlacklistService.isTokenBlacklisted(token1), is(true));
        assertThat(tokenBlacklistService.isTokenBlacklisted(token2), is(false));
    }
}
