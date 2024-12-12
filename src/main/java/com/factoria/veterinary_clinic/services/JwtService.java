package com.factoria.veterinary_clinic.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import com.factoria.veterinary_clinic.enums.Role;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUsername(String token) {
        try {
            String email = extractClaim(token, claims -> claims.get("email", String.class));
            System.out.println("Extracted email from token: " + email);
            return email;
        } catch (Exception e) {
            System.out.println("Error extracting username from token: " + e.getMessage());
            throw new RuntimeException("Error extracting username from token", e);
        }
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(String email, Role role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("email", email);
        System.out.println("Generating token for email: " + email + " with role: " + role);
        return generateToken(claims, email);
    }

    public String generateToken(Map<String, Object> extraClaims, String email) {
        System.out.println("Generating token for email: " + email + " with extra claims: " + extraClaims);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, String email) {
        final String tokenEmail = extractUsername(token);
        boolean isValid = (tokenEmail.equals(email)) && !isTokenExpired(token);
        System.out.println("Token validation result for email: " + email + " - " + isValid);
        return isValid;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = java.util.Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
