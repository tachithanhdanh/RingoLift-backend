package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Goal;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.repositories.GoalRepository;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.requests.GoalRequest;
import com.gorgeous.ringolift.responses.GoalResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    @Override
    public GoalResponse createGoal(GoalRequest goalRequest) throws DataNotFoundException {
        // Get user
        User user = userRepository.findById(goalRequest.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        // Create goal
        Goal goal = Goal.builder()
                .user(user)
                .timeSpent(goalRequest.getTimeSpent())
                .lessonCount(goalRequest.getLessonCount())
                .wordCount(goalRequest.getWordCount())
                .build();
        return GoalResponse.fromGoal(goalRepository.save(goal));
    }

    @Override
    public GoalResponse getGoalById(Long goalId) throws DataNotFoundException {
        return goalRepository.findById(goalId)
                .map(GoalResponse::fromGoal)
                .orElseThrow(() -> new DataNotFoundException("Goal not found"));
    }

    @Override
    public GoalResponse getGoalByUserId(Long userId) throws DataNotFoundException {
        return GoalResponse.fromGoal(goalRepository.findByUserId(userId));
    }

    @Override
    public List<GoalResponse> getAllGoals() {
        return goalRepository.findAll().stream().map(GoalResponse::fromGoal).toList();
    }

    @Override
    public GoalResponse updateGoal(Long goalId, GoalRequest goalRequest) throws DataNotFoundException {
        Goal goal = goalRepository.findById(goalId)
                .orElseThrow(() -> new DataNotFoundException("Goal not found"));
        User user = userRepository.findById(goalRequest.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));
        goal.setUser(user);
        goal.setTimeSpent(goalRequest.getTimeSpent());
        goal.setLessonCount(goalRequest.getLessonCount());
        goal.setWordCount(goalRequest.getWordCount());
        return GoalResponse.fromGoal(goalRepository.save(goal));
    }

    @Override
    @Transactional
    public void deleteGoal(Long goalId) throws DataNotFoundException {
        if (!goalRepository.existsById(goalId)) {
            throw new DataNotFoundException("Goal not found");
        }
        goalRepository.deleteById(goalId);
    }
}
