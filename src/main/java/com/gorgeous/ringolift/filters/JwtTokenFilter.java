package com.gorgeous.ringolift.filters;

import com.gorgeous.ringolift.exceptions.UnauthorizedException;
import com.gorgeous.ringolift.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    @Value("${api.prefix}")
    private String apiPrefix;

    public final List<String> PUBLIC_ENDPOINTS = Arrays.stream(new String[]{
                    "/auth/register", "/auth/login", "/auth/validate-token", "/auth/logout"
            }).map(endpoint -> apiPrefix + endpoint)
            .toList();

    private final UserDetailsService userDetailsService;

    private final HandlerExceptionResolver handlerExceptionResolver;

    // userDetailsService is used to load the user details from the database
    // it is injected via the constructor, and it is a bean defined in the SecurityConfig class
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // Check if the request has a token
        // If the token is valid, set the authentication in the SecurityContext
        // Continue with the filter chain
        // enable bypass for some requests
        if (isBypassToken(request)) {
            filterChain.doFilter(request, response); // enable bypass for some requests
            return;
        }
        try {
            // check if the request has a token
            // if the token is valid, set the authentication in the SecurityContext
            // continue with the filter chain
            // if the token is invalid, return an error response
            // if the token is missing, return an error response
            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                SecurityContextHolder.clearContext();
                throw new UnauthorizedException("Missing or invalid token");
            }
            String token = authHeader.substring(
                    "Bearer ".length()); // remove "Bearer " from the token
            String username = jwtUtils.extractUsername(token);
            // if the phone number is not null and the authentication is null then we set the authentication
            // via the userDetailsService
            if (username != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                // get the user details from the userDetailsService
                var userDetails = userDetailsService.loadUserByUsername(username);
                // validate the token
                if (jwtUtils.validateToken(token, userDetails)) {
                    var authenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );
                    authenticationToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                } else {
                    throw new UnauthorizedException("Invalid token");
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            SecurityContextHolder.clearContext();
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }

    }

    private boolean isBypassToken(@NonNull HttpServletRequest request) {
        final List<Pair<String, String>> bypassTokens = PUBLIC_ENDPOINTS.stream()
                .map(endpoint -> {
                    return Pair.of(endpoint, HttpMethod.POST.name());
                }).toList();
        for (Pair<String, String> bypassToken : bypassTokens) {
            if (request.getRequestURI().equals(bypassToken.getFirst())
                    && request.getMethod().equals(bypassToken.getSecond())) {
                return true;
            }
        }
        return false;
    }
}