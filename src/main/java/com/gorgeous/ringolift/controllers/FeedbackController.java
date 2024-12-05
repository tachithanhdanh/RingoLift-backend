package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.FeedbackRequest;
import com.gorgeous.ringolift.responses.FeedbackResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackController.class);

    private final FeedbackService feedbackService;

    /**
     * Create a new feedback.
     * POST /api/v1/feedbacks
     */
    @PostMapping
    public ResponseEntity<ResponseObject> createFeedback(@Validated @RequestBody FeedbackRequest request) {
        try {
            FeedbackResponse feedbackResponse = feedbackService.createFeedback(request);
            logger.info("Feedback created successfully with ID: {}", feedbackResponse.getId());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseObject("Feedback created successfully", HttpStatus.CREATED, feedbackResponse));
        } catch (Exception e) {
            logger.error("Error creating feedback: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error creating feedback", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Retrieve feedback by ID.
     * GET /api/v1/feedbacks/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getFeedback(@PathVariable Long id) {
        try {
            FeedbackResponse feedbackResponse = feedbackService.getFeedback(id);
            return ResponseEntity.ok(new ResponseObject("Feedback retrieved successfully", HttpStatus.OK, feedbackResponse));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Feedback not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            logger.error("Error retrieving feedback: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error retrieving feedback", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Retrieve feedbacks for a specific user and lesson.
     * GET /api/v1/feedbacks/user/{userId}/lesson/{lessonId}
     */
    @GetMapping("/user/{userId}/lesson/{lessonId}")
    public ResponseEntity<ResponseObject> getFeedbacksByUserAndLesson(
            @PathVariable Long userId,
            @PathVariable Long lessonId) {
        try {
            List<FeedbackResponse> feedbackResponses = feedbackService.getFeedbacksByUserAndLesson(userId, lessonId);
            if (feedbackResponses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("No feedback found", HttpStatus.NOT_FOUND, null));
            }
            return ResponseEntity.ok(new ResponseObject("Feedbacks retrieved successfully", HttpStatus.OK, feedbackResponses));
        } catch (Exception e) {
            logger.error("Error retrieving feedbacks by user and lesson: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error retrieving feedbacks", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Update an existing feedback.
     * PUT /api/v1/feedbacks/update/{feedbackId}
     */
    @PutMapping("/update/{feedbackId}")
    public ResponseEntity<ResponseObject> updateFeedback(
            @PathVariable Long feedbackId, // Add feedbackId here
            @Validated @RequestBody FeedbackRequest request) {
        try {
            FeedbackResponse feedbackResponse = feedbackService.updateFeedback(feedbackId, request);
            return ResponseEntity.ok(new ResponseObject("Feedback updated successfully", HttpStatus.OK, feedbackResponse));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Feedback not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            logger.error("Error updating feedback: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error updating feedback", HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Delete a feedback by ID, user ID, and lesson ID.
     * DELETE /api/v1/feedbacks/{feedbackId}/user/{userId}/lesson/{lessonId}
     */
    @DeleteMapping("/{feedbackId}/user/{userId}/lesson/{lessonId}")
    public ResponseEntity<ResponseObject> deleteFeedback(
            @PathVariable Long feedbackId,
            @PathVariable Long userId,
            @PathVariable Long lessonId) {
        logger.info("Deleting feedback with Feedback ID: {} for User ID: {} and Lesson ID: {}", feedbackId, userId, lessonId);

        try {
            feedbackService.deleteFeedback(feedbackId, userId, lessonId);
            logger.info("Feedback deleted successfully with Feedback ID: {} for User ID: {} and Lesson ID: {}", feedbackId, userId, lessonId);

            return ResponseEntity.ok(new ResponseObject("Feedback deleted successfully", HttpStatus.OK, null));

        } catch (DataNotFoundException e) {
            logger.error("Data not found while deleting feedback: Feedback ID: {}, User ID: {}, Lesson ID: {}", feedbackId, userId, lessonId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Feedback not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            logger.error("Unexpected error while deleting feedback", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error deleting feedback: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }
}
