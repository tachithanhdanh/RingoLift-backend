package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.GoalRequest;
import com.gorgeous.ringolift.responses.GoalResponse;
import java.util.List;

public interface GoalService {
    public GoalResponse createGoal(GoalRequest goalRequest) throws DataNotFoundException;
    public GoalResponse getGoalById(Long goalId) throws DataNotFoundException;
    public GoalResponse getGoalByUserId(Long userId) throws DataNotFoundException;
    public List<GoalResponse> getAllGoals();
    public GoalResponse updateGoal(Long goalId, GoalRequest goalRequest) throws DataNotFoundException;
    public void deleteGoal(Long goalId) throws DataNotFoundException;

}
