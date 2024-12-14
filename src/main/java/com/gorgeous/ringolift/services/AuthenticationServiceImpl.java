package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.components.JwtUtils;
import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.InvalidParamException;
import com.gorgeous.ringolift.exceptions.PermissionDenyException;
import com.gorgeous.ringolift.models.Goal;
import com.gorgeous.ringolift.models.Role;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.models.UserGender;
import com.gorgeous.ringolift.repositories.GoalRepository;
import com.gorgeous.ringolift.repositories.RoleRepository;
import com.gorgeous.ringolift.repositories.UserGenderRepository;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.requests.ChangePasswordRequest;
import com.gorgeous.ringolift.requests.LoginRequest;
import com.gorgeous.ringolift.requests.LogoutRequest;
import com.gorgeous.ringolift.requests.UserRegisterRequest;
import com.gorgeous.ringolift.requests.UserRequest;
import com.gorgeous.ringolift.requests.ValidateTokenRequest;
import com.gorgeous.ringolift.responses.ChangePasswordResponse;
import com.gorgeous.ringolift.responses.LoginResponse;
import com.gorgeous.ringolift.responses.LogoutResponse;
import com.gorgeous.ringolift.responses.UserResponse;
import com.gorgeous.ringolift.responses.ValidateTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserGenderRepository userGenderRepository;

    private final GoalRepository goalRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtils jwtUtils;

    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse register(UserRegisterRequest userRegisterRequest)
            throws DataNotFoundException, PermissionDenyException {
        // When a user registers, only these properties are initially set
        // username, email, firstName, lastName
        // dateOfBirth, gender, password, isPublic, role

        // Initially, the google Id is not set

        UserGender gender = null;
        if (userRegisterRequest.getGenderId() != null) {
            gender = userGenderRepository.findById(userRegisterRequest.getGenderId()).orElse(null);
        }

        Role role = roleRepository.findByName(userRegisterRequest.getRole().toUpperCase())
                .orElseThrow(() -> new DataNotFoundException(
                        String.format("Role %s not found", userRegisterRequest.getRole())));

        if (role.getName().toUpperCase().equals(Role.ADMIN)) {
            throw new PermissionDenyException("You are not allowed to create an admin user");
        }

        // encode the password using BCryptPasswordEncoder
        String password = userRegisterRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(password);


        // convert from userRequest to User
        User newUser = User.builder()
                .username(userRegisterRequest.getUsername())
                .email(userRegisterRequest.getEmail())
                .firstName(userRegisterRequest.getFirstName())
                .lastName(userRegisterRequest.getLastName())
                .dateOfBirth(userRegisterRequest.getDateOfBirth())
                .gender(gender)
                .password(encodedPassword)
                .isPublic(userRegisterRequest.getIsPublic())
                .role(role)
                .build();

        // save the user to the database
        return UserResponse.fromUser(userRepository.save(newUser));

    }

    @Override
    public UserResponse login(LoginRequest loginRequest)
            throws DataNotFoundException, InvalidParamException {
        User existingUser = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        // check if the user has a password
        // only check if user signs in with phone number and password
        // not check if user signs in with Google
        if (existingUser.getGoogleId() == null || existingUser.getGoogleId().isEmpty()) {
            if (!passwordEncoder.matches(loginRequest.getPassword(), existingUser.getPassword())) {
                throw new BadCredentialsException("Invalid username or password");
            }
        }

        // authenticate user with Spring Security
        var authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword(),
                existingUser.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);

        String token = jwtUtils.generateToken(existingUser);

        existingUser.setAccessToken(token);

        return UserResponse.fromUser(userRepository.save(existingUser));
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest)
            throws DataNotFoundException, InvalidParamException {
        // Find the user by username
        User existingUser = userRepository.findByUsername(changePasswordRequest.getUsername())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        // Check if the user has a Google ID
        if (existingUser.getGoogleId() == null || existingUser.getGoogleId().isEmpty()) {
            // If the user does not have a Google ID, check the old password
            if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), existingUser.getPassword())) {
                throw new InvalidParamException("Incorrect old password");
            }
        }

        // Encode the new password
        String newPassword = changePasswordRequest.getNewPassword();
        String encodedNewPassword = passwordEncoder.encode(newPassword);

        // Set the new password
        existingUser.setPassword(encodedNewPassword);

        // Save the user to the database
        userRepository.save(existingUser);

        // Return the response
        ChangePasswordResponse response = new ChangePasswordResponse();
        response.setMessage("Password changed successfully");
        return response;
    }


    @Override
    public ValidateTokenResponse validateToken(ValidateTokenRequest request) {
        return null;
    }


    @Override
    public LogoutResponse logout(LogoutRequest request) {
        return null;
    }
}
