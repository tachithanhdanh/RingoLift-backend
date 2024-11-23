package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.UserRequest;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.responses.UserResponse;
import com.gorgeous.ringolift.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("${api.prefix}/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Create a new user
    @PostMapping("")
    public ResponseEntity<ResponseObject> createUser(
            @Valid @RequestBody UserRequest userRequest
    ) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("User created successfully")
                        .status(HttpStatus.CREATED)
                        .data(userResponse)
                        .build()
        );
    }

    // Get all users
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get all users successfully")
                        .status(HttpStatus.OK)
                        .data(users)
                        .build()
        );
    }

    // Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable Long id) throws DataNotFoundException {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get user by id successfully")
                        .status(HttpStatus.OK)
                        .data(userResponse)
                        .build()
        );
    }

    // Update user by id
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest userRequest
    ) throws DataNotFoundException {
        UserResponse userResponse = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Update user successfully")
                        .status(HttpStatus.OK)
                        .data(userResponse)
                        .build()
        );
    }

    // Delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Delete user successfully")
                        .status(HttpStatus.OK)
                        .data(null)
                        .build()
        );
    }
}
