package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.InvalidParamException;
import com.gorgeous.ringolift.exceptions.PermissionDenyException;
import com.gorgeous.ringolift.requests.ChangePasswordRequest;
import com.gorgeous.ringolift.requests.LoginRequest;
import com.gorgeous.ringolift.requests.LogoutRequest;
import com.gorgeous.ringolift.requests.UserRegisterRequest;
import com.gorgeous.ringolift.requests.UserRequest;
import com.gorgeous.ringolift.requests.ValidateTokenRequest;
import com.gorgeous.ringolift.responses.ChangePasswordResponse;
import com.gorgeous.ringolift.responses.LogoutResponse;
import com.gorgeous.ringolift.responses.UserResponse;
import com.gorgeous.ringolift.responses.ValidateTokenResponse;

public interface AuthenticationService {
    UserResponse register(UserRegisterRequest userRequest)
            throws DataNotFoundException, PermissionDenyException;

    UserResponse login(LoginRequest userRequest) throws DataNotFoundException, InvalidParamException;

    ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest)
            throws DataNotFoundException, InvalidParamException;

    ValidateTokenResponse validateToken(ValidateTokenRequest request);

    LogoutResponse logout(LogoutRequest request);
}
