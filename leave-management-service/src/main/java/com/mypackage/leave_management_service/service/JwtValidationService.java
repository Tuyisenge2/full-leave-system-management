package com.mypackage.leave_management_service.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class JwtValidationService {
    private final SecretKey key;
    private static final List<String> ADMIN_ROLES = Arrays.asList("ADMIN", "MANAGER");

    public JwtValidationService(@Value("${jwt.secret}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid or expired token");
        }
    }

    public String getEmailFromToken(String token) {
        Claims claims = validateToken(token);
        return claims.get("email", String.class);
    }

    public String getNameFromToken(String token) {
        Claims claims = validateToken(token);
        return claims.get("name", String.class);
    }

    public String getRoleFromToken(String token) {
        Claims claims = validateToken(token);
        return claims.get("role", String.class);
    }

    public boolean hasAdminRole(String token) {
        String role = getRoleFromToken(token);
        return ADMIN_ROLES.contains(role);
    }

    public void validateAdminRole(String token) {
        if (!hasAdminRole(token)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied. Admin or Manager role required.");
        }
    }
} 