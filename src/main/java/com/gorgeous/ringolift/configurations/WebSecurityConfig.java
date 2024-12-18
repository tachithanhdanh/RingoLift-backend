package com.gorgeous.ringolift.configurations;

import static org.springframework.http.HttpMethod.POST;

import com.gorgeous.ringolift.filters.JwtTokenFilter;
import com.gorgeous.ringolift.jwt.JwtAuthenticationEntryPoint;
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
    @Value("${api.prefix}")
    private String apiPrefix;

    public final String[] PUBLIC_ENDPOINTS = (String[]) Arrays.stream(new String[]{
                    "/auth/register", "/auth/login", "/auth/validate-token", "/auth/logout"
            }).map(endpoint -> apiPrefix + endpoint)
            .toArray();
    private final JwtTokenFilter jwtTokenFilter;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

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
                            .requestMatchers(POST, // user and admin can get categories
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
        List<String> allowedOrigins = List.of("http://localhost:5173");
        List<String> allowedMethods = List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS");
        configuration.setAllowedOrigins(allowedOrigins);
        configuration.setAllowedMethods(allowedMethods);
        configuration.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
