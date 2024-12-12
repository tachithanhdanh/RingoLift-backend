package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.requests.ChangePasswordRequest;
import com.gorgeous.ringolift.requests.LoginRequest;
import com.gorgeous.ringolift.requests.UserRequest;
import com.gorgeous.ringolift.responses.LogoutResponse;
import com.gorgeous.ringolift.responses.UserResponse;
import com.gorgeous.ringolift.responses.ValidateTokenResponse;
import org.springframework.stereotype.Service;

public interface AuthenticationService {
    UserResponse register(UserRequest userRequest);

    UserResponse login(LoginRequest userRequest);

    ChangePasswordRequest changePassword(ChangePasswordRequest changePasswordRequest);

    ValidateTokenResponse validateToken(String token);

    LogoutResponse logout(String token);
}
