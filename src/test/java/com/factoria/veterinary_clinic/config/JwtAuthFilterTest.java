package com.factoria.veterinary_clinic.config;

import com.factoria.veterinary_clinic.services.JwtService;
import com.factoria.veterinary_clinic.services.TokenBlacklistService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.context.SecurityContextHolder;
import static org.mockito.Mockito.*;

import java.io.PrintWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JwtAuthFilterTest {

    @Mock
    private JwtService jwtService;

    @Mock
    private TokenBlacklistService tokenBlacklistService;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    @Mock
    private UserDetailsService userDetailsService;

    @InjectMocks
    private JwtAuthFilter jwtAuthFilter;

    private final String validJwt = "validJwtToken";
    private final String invalidJwt = "invalidJwtToken";
    private final String username = "testUser";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        jwtAuthFilter = new JwtAuthFilter(jwtService, userDetailsService, tokenBlacklistService);
    }

    @Test
    public void shouldAllowRequestIfNoToken() throws Exception {
        SecurityContextHolder.clearContext();
        when(request.getHeader("Authorization")).thenReturn(null);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(nullValue()));
    }

    @Test
    public void shouldAllowRequestIfTokenIsBlacklisted() throws Exception {

        PrintWriter printWriter = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(printWriter);

        String blacklistedToken = "blacklistedToken";
        when(request.getHeader("Authorization")).thenReturn("Bearer " + blacklistedToken);
        when(tokenBlacklistService.isTokenBlacklisted(blacklistedToken)).thenReturn(true);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        verify(printWriter, times(1)).write("You need to log in.");

        verify(filterChain, times(0)).doFilter(request, response);
    }

    @Test
    public void shouldAuthenticateUserWithValidToken() throws Exception {

        UserDetails userDetails = User.withUsername(username).password("password").roles("USER").build();
        when(request.getHeader("Authorization")).thenReturn("Bearer " + validJwt);
        when(jwtService.extractUsername(validJwt)).thenReturn(username);
        when(jwtService.isTokenValid(validJwt, username)).thenReturn(true);
        when(userDetailsService.loadUserByUsername(username)).thenReturn(userDetails);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(notNullValue()));
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void shouldNotAuthenticateWithInvalidToken() throws Exception {

        UserDetails userDetails = User.withUsername("testUser").password("password").roles("USER").build();

        when(request.getHeader("Authorization")).thenReturn("Bearer " + invalidJwt);
        when(jwtService.extractUsername(invalidJwt)).thenReturn("testUser");
        when(jwtService.isTokenValid(invalidJwt, "testUser")).thenReturn(false);
        when(userDetailsService.loadUserByUsername("testUser")).thenReturn(userDetails);

        jwtAuthFilter.doFilterInternal(request, response, filterChain);

        assertThat(SecurityContextHolder.getContext().getAuthentication(), is(nullValue()));

        verify(filterChain, times(1)).doFilter(request, response);
    }

}
