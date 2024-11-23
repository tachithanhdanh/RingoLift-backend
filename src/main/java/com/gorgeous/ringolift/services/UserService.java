package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.UserRequest;
import com.gorgeous.ringolift.responses.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse getUserById(Long id) throws DataNotFoundException;
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UserRequest userRequest) throws DataNotFoundException;
    void deleteUser(Long id);
}
