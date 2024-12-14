package com.gorgeous.ringolift.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Custom implementation of AuthenticationEntryPoint.
 *
 * This class is used to handle authentication exceptions and provide custom responses
 * when a user is unauthorized. It is typically invoked when an unauthenticated user
 * tries to access a protected resource that requires authentication (e.g., a resource
 * behind a JWT-based authentication mechanism).
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Handles the case when authentication fails, typically due to a missing or invalid token.
     *
     * @param request The HttpServletRequest that contains the request data
     * @param response The HttpServletResponse that will contain the error response
     * @param authException The exception that was thrown during authentication
     *
     * This method is triggered when a request is made to a protected resource but the
     * user is not authenticated (e.g., missing or invalid JWT token).
     * The method sends a 401 Unauthorized status code along with a message indicating
     * the reason for the failure.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        // Send a 401 Unauthorized error with a message explaining the issue
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Token missing or invalid");
    }
}