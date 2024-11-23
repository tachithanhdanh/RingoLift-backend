package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Goal;
import com.gorgeous.ringolift.requests.GoalRequest;
import com.gorgeous.ringolift.responses.GoalResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/goals")
@RequiredArgsConstructor
public class GoalController {

    private final GoalService goalService;

    // Create a new goal
    // POST http://localhost:8088/api/v1/goals
    @PostMapping("")
    public ResponseEntity<ResponseObject> createGoal(
            @Valid @RequestBody GoalRequest goalRequest
    ) throws DataNotFoundException {
        GoalResponse goalResponse = goalService.createGoal(goalRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Create goal successfully")
                        .status(HttpStatus.CREATED)
                        .data(goalResponse)
                        .build()
        );
    }

    // Get all goals
    // GET http://localhost:8088/api/v1/goals
    @GetMapping("")
    public ResponseEntity<ResponseObject> getGoals() {
        List<GoalResponse> goalResponses = goalService.getAllGoals();
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get list of goals successfully")
                        .status(HttpStatus.OK)
                        .data(goalResponses)
                        .build()
        );
    }

    // Get a goal by id
    // GET http://localhost:8088/api/v1/goals/1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getGoal(
            @Valid @PathVariable Long id
    ) throws DataNotFoundException {
        GoalResponse goalResponse = goalService.getGoalById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get book by id successfully")
                        .status(HttpStatus.OK)
                        .data(goalResponse)
                        .build()
        );
    }

    // Update a goal by id
    // PUT http://localhost:8088/api/v1/goals/1
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateGoal(
            @Valid @PathVariable Long id,
            @Valid @RequestBody GoalRequest goalRequest
    ) throws DataNotFoundException {
        GoalResponse goalResponse = goalService.updateGoal(id, goalRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Update goal successfully")
                        .status(HttpStatus.OK)
                        .data(goalResponse)
                        .build()
        );
    }

    // Delete a goal by id
    // DELETE httpL//localhost:8088/api/v1/goals/1
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteGoal(
            @Valid @PathVariable Long id
    ) throws DataNotFoundException {
        goalService.deleteGoal(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Delete goal successfully")
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}
