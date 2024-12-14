package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.InvalidParamException;
import com.gorgeous.ringolift.exceptions.PermissionDenyException;
import com.gorgeous.ringolift.requests.ChangePasswordRequest;
import com.gorgeous.ringolift.requests.LoginRequest;
import com.gorgeous.ringolift.requests.LogoutRequest;
import com.gorgeous.ringolift.requests.UserRegisterRequest;
import com.gorgeous.ringolift.requests.ValidateTokenRequest;
import com.gorgeous.ringolift.responses.ChangePasswordResponse;
import com.gorgeous.ringolift.responses.LoginResponse;
import com.gorgeous.ringolift.responses.LogoutResponse;
import com.gorgeous.ringolift.responses.ValidateTokenResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("${api.prefix}/auth")
@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    // Register new user
    @PostMapping("/register")
    public ResponseEntity<ResponseObject> register(@Valid @RequestBody UserRegisterRequest userRegisterRequest)
            throws DataNotFoundException, PermissionDenyException {
        var userResponse = authenticationService.register(userRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("User registered successfully")
                        .status(HttpStatus.CREATED)
                        .data(userResponse)
                        .build()
        );
    }

    // Login user and return token
    @PostMapping("/login")
    public ResponseEntity<ResponseObject> login(@Valid @RequestBody LoginRequest loginRequest)
            throws DataNotFoundException, InvalidParamException {
        var loginResponse = authenticationService.login(loginRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Login successful")
                        .status(HttpStatus.OK)
                        .data(loginResponse)
                        .build()
        );
    }

    // Xác thực tính hợp lệ của token
    @PostMapping("/validate-token")
    public ResponseEntity<ResponseObject> validateToken(@Valid @RequestBody ValidateTokenRequest validateTokenRequest)
            throws DataNotFoundException {
        var validateTokenResponse = authenticationService.validateToken(validateTokenRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Token validation complete")
                        .status(HttpStatus.OK)
                        .data(validateTokenResponse)
                        .build()
        );
    }

    // Đăng xuất người dùng
    @PostMapping("/logout")
    public ResponseEntity<ResponseObject> logout(@Valid @RequestBody LogoutRequest logoutRequest)
            throws DataNotFoundException, PermissionDenyException, InvalidParamException {
        var logoutResponse = authenticationService.logout(logoutRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Logout successful")
                        .status(HttpStatus.OK)
                        .data(logoutResponse)
                        .build()
        );
    }

    // Thay đổi mật khẩu
    @PutMapping("/change-password")
    public ResponseEntity<ResponseObject> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest)
            throws DataNotFoundException, InvalidParamException {
        var changePasswordResponse = authenticationService.changePassword(changePasswordRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Password changed successfully")
                        .status(HttpStatus.OK)
                        .data(changePasswordResponse)
                        .build()
        );
    }
}
