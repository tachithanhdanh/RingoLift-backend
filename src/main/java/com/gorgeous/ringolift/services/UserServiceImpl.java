package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Goal;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.models.UserGender;
import com.gorgeous.ringolift.repositories.GoalRepository;
import com.gorgeous.ringolift.repositories.UserGenderRepository;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.requests.UserRequest;
import com.gorgeous.ringolift.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserGenderRepository userGenderRepository;
    private final GoalRepository goalRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        UserGender gender = null;
        if (userRequest.getGenderId() != null) {
            gender = userGenderRepository.findById(userRequest.getGenderId())
                    .orElse(null);
        }

        Goal goal = null;
        if (userRequest.getGoalId() != null) {
            goal = goalRepository.findById(userRequest.getGoalId())
                    .orElse(null);
        }

        User user = User.builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .dateOfBirth(userRequest.getDateOfBirth())
                .gender(gender)
                .picture(userRequest.getPicture())
                .goal(goal)
                .password(userRequest.getPassword())
                .isPublic(userRequest.getIsPublic())
                .googleId(userRequest.getGoogleId())
                .accessToken(userRequest.getAccessToken())
                .build();

        return UserResponse.fromUser(userRepository.save(user));
    }

    @Override
    public UserResponse getUserById(Long id) throws DataNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id " + id));
        return UserResponse.fromUser(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::fromUser)
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) throws DataNotFoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id " + id));

        if (userRequest.getUsername() != null) {
            existingUser.setUsername(userRequest.getUsername());
        }
        if (userRequest.getEmail() != null) {
            existingUser.setEmail(userRequest.getEmail());
        }
        existingUser.setFirstName(userRequest.getFirstName());
        existingUser.setLastName(userRequest.getLastName());
        existingUser.setDateOfBirth(userRequest.getDateOfBirth());

        if (userRequest.getGenderId() != null) {
            UserGender gender = userGenderRepository.findById(userRequest.getGenderId())
                    .orElse(null);
            existingUser.setGender(gender);
        }

        existingUser.setPicture(userRequest.getPicture());

        if (userRequest.getGoalId() != null) {
            Goal goal = goalRepository.findById(userRequest.getGoalId())
                    .orElse(null);
            existingUser.setGoal(goal);
        }

        existingUser.setPassword(userRequest.getPassword());
        existingUser.setIsPublic(userRequest.getIsPublic());
        existingUser.setGoogleId(userRequest.getGoogleId());
        existingUser.setAccessToken(userRequest.getAccessToken());

        return UserResponse.fromUser(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
