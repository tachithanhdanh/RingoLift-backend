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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;

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
    public ValidateTokenResponse validateToken(ValidateTokenRequest request)
            throws DataNotFoundException {
        User existingUser = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        String token = request.getToken();

        // Kiểm tra tính hợp lệ của token
        boolean isValid = jwtUtils.validateToken(token, existingUser);  // null có thể là username nếu bạn cần kiểm tra đối chiếu
        ValidateTokenResponse response = new ValidateTokenResponse();

        if (isValid) {
            // Token hợp lệ, trích xuất thông tin từ token
            String username = jwtUtils.extractUsername(token);
            Date expirationDate = jwtUtils.getExpirationDate(token);

            response.setUsername(username);
            response.setExpirationDate(expirationDate);
            response.setValid(true);
            response.setMessage("Token is valid");
        } else {
            // Token không hợp lệ
            response.setValid(false);
            response.setMessage("Token is invalid or expired");
        }

        return response;
    }



    @Override
    public LogoutResponse logout(LogoutRequest request)
            throws DataNotFoundException, PermissionDenyException, InvalidParamException {
        // Remove access token from the user
        // Get the current user store in the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String token = request.getToken();

        if (authentication == null) {
            throw new PermissionDenyException("You are not logged in");
        }

        if (!request.getUsername().equals(authentication.getName())) {
            throw new PermissionDenyException("You are not allowed to log out this user");
        }

        String username = authentication.getName();

        // Get the user from the database
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        if (jwtUtils.validateToken(token, existingUser)) {
            existingUser.setAccessToken(null);
            userRepository.save(existingUser);
        } else {
            throw new InvalidParamException("Invalid token");
        }
        return new LogoutResponse("Logout successfully");
    }
}
