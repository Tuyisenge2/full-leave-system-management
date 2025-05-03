package com.mypackage.leave_management_service.config;

import com.mypackage.leave_management_service.service.JwtValidationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.server.ResponseStatusException;

@Component
public class SecurityInterceptor implements HandlerInterceptor {
    private final JwtValidationService jwtValidationService;

    public SecurityInterceptor(JwtValidationService jwtValidationService) {
        this.jwtValidationService = jwtValidationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Allow OPTIONS requests through without authentication
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "No token provided");
            return false;
        }

        String token = authHeader.substring(7);
        try {
            // Validate token
            jwtValidationService.validateToken(token);
            return true;
        } catch (ResponseStatusException e) {
            sendErrorResponse(response, HttpStatus.valueOf(e.getStatusCode().value()), e.getReason());
            return false;
        } catch (Exception e) {
            sendErrorResponse(response, HttpStatus.UNAUTHORIZED, "Invalid or expired token");
            return false;
        }
    }

    private void sendErrorResponse(HttpServletResponse response, HttpStatus status, String message) {
        response.setStatus(status.value());
        response.setContentType("application/json");
        try {
            response.getWriter().write(String.format(
                "{\"status\":\"error\",\"message\":\"%s\"}", 
                message
            ));
        } catch (Exception e) {
            // Log error
        }
    }
} 