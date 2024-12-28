package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Question;
import com.gorgeous.ringolift.responses.UserAnswerResponse;
import com.gorgeous.ringolift.requests.UserAnswerRequest;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/user-answers")
@CrossOrigin(origins = "*")
public class UserAnswerController {
    private final UserAnswerService userAnswerService;

    /**
     * Create a new UserAnswer.
     * POST /api/v1/user-answers
     */
    @PostMapping
    public ResponseEntity<ResponseObject> createUserAnswer(@Valid @RequestBody UserAnswerRequest request) {

        try {
            UserAnswerResponse response = userAnswerService.createUserAnswer(request);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseObject("UserAnswer created successfully", HttpStatus.CREATED, response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error creating UserAnswer: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Update an existing UserAnswer based on its ID.
     * PUT /api/v1/user-answers/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateUserAnswer(
            @PathVariable Long id,
            @Valid @RequestBody UserAnswerRequest request) {

        try {
            UserAnswerResponse response = userAnswerService.updateUserAnswer(id, request);

            return ResponseEntity.ok(new ResponseObject("UserAnswer updated successfully", HttpStatus.OK, response));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("UserAnswer not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error updating UserAnswer: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Retrieve a specific UserAnswer by id.
     * GET /api/v1/user-answers/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUserAnswerById(
            @PathVariable Long id) {

        try {
            UserAnswerResponse response = userAnswerService.getUserAnswerById(id);
            if (response == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("UserAnswer not found", HttpStatus.NOT_FOUND, null));
            }

            return ResponseEntity.ok(new ResponseObject("UserAnswer fetched successfully", HttpStatus.OK, response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error fetching UserAnswer: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Retrieve all UserAnswers for a specific user.
     * GET /api/v1/user-answers/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseObject> getAllUserAnswersByUserId(
            @PathVariable Long userId) {

        try {
            List<UserAnswerResponse> responses = userAnswerService.getUserAnswersByUserId(userId);
            if (responses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("No UserAnswers found for this user", HttpStatus.NOT_FOUND, null));
            }

            return ResponseEntity.ok(new ResponseObject("UserAnswers fetched successfully", HttpStatus.OK, responses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error fetching UserAnswers: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Retrieve all UserAnswers for a specific question.
     * GET /api/v1/user-answers/question/{questionId}
     */
    @GetMapping("/question/{questionId}")
    public ResponseEntity<ResponseObject> getAllUserAnswersByQuestionId(
            @PathVariable Long questionId) {

        try {
            List<UserAnswerResponse> responses = userAnswerService.getUserAnswersByQuestionId(questionId);
            if (responses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("No UserAnswers found for this question", HttpStatus.NOT_FOUND, null));
            }

            return ResponseEntity.ok(new ResponseObject("UserAnswers fetched successfully", HttpStatus.OK, responses));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error fetching UserAnswers: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Delete a UserAnswer by id.
     * DELETE /api/v1/user-answers/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteUserAnswer(
            @PathVariable Long id) {

        try {
            userAnswerService.deleteUserAnswer(id);

            return ResponseEntity.ok(new ResponseObject("UserAnswer deleted successfully", HttpStatus.OK, null));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("UserAnswer not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error deleting UserAnswer: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }
    /**
     * Check if a UserAnswer is correct.
     * POST /api/v1/user-answers/{id}/check
     */
    @PostMapping("/{id}/check")
    public ResponseEntity<ResponseObject> checkUserAnswer(
            @PathVariable Long id) {

        try {
            // Lấy UserAnswer từ service
            UserAnswerResponse userAnswerResponse = userAnswerService.getUserAnswerById(id);

            if (userAnswerResponse == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("UserAnswer not found", HttpStatus.NOT_FOUND, null));
            }

            // Lấy câu hỏi liên quan từ UserAnswer
            Question question = userAnswerService.getQuestionByUserAnswerId(id);

            if (question == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("Related Question not found", HttpStatus.NOT_FOUND, null));
            }

            // Kiểm tra câu trả lời có khớp với correctAnswer không
            boolean isCorrect = userAnswerResponse.getAnswerText().equalsIgnoreCase(question.getCorrectAnswer());

            return ResponseEntity.ok(
                    new ResponseObject("Check result fetched successfully",
                            HttpStatus.OK,
                            isCorrect));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error checking UserAnswer: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }
}
