package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.components.JwtUtils;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.repositories.RoleRepository;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.requests.ChangePasswordRequest;
import com.gorgeous.ringolift.requests.LoginRequest;
import com.gorgeous.ringolift.requests.UserRequest;
import com.gorgeous.ringolift.responses.LogoutResponse;
import com.gorgeous.ringolift.responses.UserResponse;
import com.gorgeous.ringolift.responses.ValidateTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse register(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse login(LoginRequest userRequest) {
        return null;
    }

    @Override
    public ChangePasswordRequest changePassword(ChangePasswordRequest changePasswordRequest) {
        return null;
    }

    @Override
    public ValidateTokenResponse validateToken(String token) {
        return null;
    }

    @Override
    public LogoutResponse logout(String token) {
        return null;
    }
}
