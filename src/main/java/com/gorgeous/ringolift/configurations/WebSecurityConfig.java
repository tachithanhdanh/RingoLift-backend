package com.gorgeous.ringolift.configurations;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import com.gorgeous.ringolift.constants.ApiConstants;
import com.gorgeous.ringolift.filters.JwtTokenFilter;
import com.gorgeous.ringolift.jwt.JwtAuthenticationEntryPoint;
import jakarta.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    @Value("${api.prefix}")
    private String apiPrefix;

    // PUBLIC_ENDPOINTS must be initialized after apiPrefix is set, so it will be initialized in the @PostConstruct method
    public String[] PUBLIC_ENDPOINTS;

    /**
     * This method is called after all dependencies have been injected by Spring.
     * It initializes PUBLIC_ENDPOINTS using the apiPrefix value, which is guaranteed to be set by this point.
     */
    @PostConstruct
    private void init() {
        PUBLIC_ENDPOINTS = ApiConstants.PUBLIC_ENDPOINTS.stream()
                .map(endpoint -> apiPrefix + endpoint)
                .toArray(String[]::new);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling((httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(
                                jwtAuthenticationEntryPoint)))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests((requests) -> {
                    requests
                            .requestMatchers(POST,// user and admin can get categories
                                    PUBLIC_ENDPOINTS)
                            .permitAll()
                            .requestMatchers(GET,// user and admin can get categories
                                    PUBLIC_ENDPOINTS)
                            .permitAll()
                            .anyRequest().authenticated(); // allow all requests
                });
        return http.build();
    }

    // https://github.com/vuongnhattin/Spring-Coursera/blob/master/src/main/java/com/tin/springcoursera/config/SecurityConfig.java
    // https://www.baeldung.com/spring-cors
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

// Allow any origin
        configuration.setAllowedOriginPatterns(List.of("*")); // Use setAllowedOrigins(List.of("*")) for older Spring versions

// Allow specific HTTP methods
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

// Allow any headers
        configuration.setAllowedHeaders(List.of("*"));

// Allow credentials if needed (optional, remove this if not required)
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
