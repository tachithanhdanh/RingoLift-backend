package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.responses.MistakeResponse;
import com.gorgeous.ringolift.requests.MistakeRequest;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.MistakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/mistakes")
public class MistakeController {

    private static final Logger logger = LoggerFactory.getLogger(MistakeController.class);

    private final MistakeService mistakeService;

    public MistakeController(MistakeService mistakeService) {
        this.mistakeService = mistakeService;
        logger.info("MistakeController initialized");
    }

    /**
     * Create a new mistake.
     * POST /api/v1/mistakes
     */
    @PostMapping
    public ResponseEntity<ResponseObject> createMistake(@Valid @RequestBody MistakeRequest request) {
        logger.info("Received create mistake request - Request: {}", request);

        try {
            MistakeResponse response = mistakeService.createMistake(request);
            logger.info("Mistake created successfully with ID: {}", response.getId());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseObject("Mistake created successfully", HttpStatus.CREATED, response));
        } catch (Exception e) {
            logger.error("Unexpected error while creating mistake", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error creating mistake: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Update an existing mistake based on its ID.
     * PUT /api/v1/mistakes/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateMistake(@PathVariable Long id, @Valid @RequestBody MistakeRequest request) {
        logger.info("Received update mistake request - Mistake ID: {}, Request: {}", id, request);

        try {
            MistakeResponse response = mistakeService.updateMistake(id, request);
            logger.info("Mistake updated successfully with ID: {}", response.getId());

            return ResponseEntity.ok(new ResponseObject("Mistake updated successfully", HttpStatus.OK, response));
        } catch (DataNotFoundException e) {
            logger.error("Data not found while updating mistake: ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Mistake not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            logger.error("Unexpected error while updating mistake: ID: {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error updating mistake: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Retrieve a specific mistake for a user, lesson, and question by its id.
     * GET /api/v1/mistakes/{userId}/{lessonId}/{questionId}/{id}
     */
    @GetMapping("/{userId}/{lessonId}/{questionId}/{id}")
    public ResponseEntity<ResponseObject> getMistakeById(
            @PathVariable Long userId,
            @PathVariable Long lessonId,
            @PathVariable Long questionId,
            @PathVariable Long id) {
        logger.info("Retrieving mistake for User ID: {}, Lesson ID: {}, Question ID: {}, Mistake ID: {}",
                userId, lessonId, questionId, id);

        try {
            MistakeResponse response = mistakeService.getMistakeById(userId, lessonId, questionId, id);
            if (response == null) {
                logger.info("Mistake not found for User ID: {}, Lesson ID: {}, Question ID: {}, Mistake ID: {}",
                        userId, lessonId, questionId, id);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("Mistake not found", HttpStatus.NOT_FOUND, null));
            }

            logger.info("Found mistake for User ID: {}, Lesson ID: {}, Question ID: {}, Mistake ID: {}",
                    userId, lessonId, questionId, id);
            return ResponseEntity.ok(new ResponseObject("Mistake fetched successfully", HttpStatus.OK, response));
        } catch (Exception e) {
            logger.error("Unexpected error while fetching mistake for User ID: {}, Lesson ID: {}, Question ID: {}, Mistake ID: {}",
                    userId, lessonId, questionId, id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error fetching mistake: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }
    /**
     * Retrieve all mistakes for a specific user in a specific lesson.
     * GET /api/v1/mistakes/{userId}/{lessonId}
     */
    @GetMapping("/{userId}/{lessonId}")
    public ResponseEntity<ResponseObject> getAllMistakesForLesson(
            @PathVariable Long userId,
            @PathVariable Long lessonId) {
        logger.info("Retrieving all mistakes for User ID: {}, Lesson ID: {}", userId, lessonId);

        try {
            List<MistakeResponse> responses = mistakeService.getMistakesByLessonAndUserId(userId, lessonId);
            if (responses.isEmpty()) {
                logger.info("No mistakes found for User ID: {}, Lesson ID: {}", userId, lessonId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("No mistakes found for this lesson and user", HttpStatus.NOT_FOUND, null));
            }

            logger.info("Found {} mistakes for User ID: {}, Lesson ID: {}", responses.size(), userId, lessonId);
            return ResponseEntity.ok(new ResponseObject("Mistakes fetched successfully", HttpStatus.OK, responses));
        } catch (Exception e) {
            logger.error("Unexpected error while fetching mistakes for User ID: {}, Lesson ID: {}", userId, lessonId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error fetching mistakes: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Delete a mistake by user_id, lesson_id, question_id, and mistake_id.
     * DELETE /api/v1/mistakes/{userId}/{lessonId}/{questionId}/{id}
     */
    @DeleteMapping("/{userId}/{lessonId}/{questionId}/{id}")
    public ResponseEntity<ResponseObject> deleteMistake(
            @PathVariable Long userId,
            @PathVariable Long lessonId,
            @PathVariable Long questionId,
            @PathVariable Long id) {

        logger.info("Deleting mistake for User ID: {}, Lesson ID: {}, Question ID: {}, Mistake ID: {}",
                userId, lessonId, questionId, id);

        try {
            mistakeService.deleteMistakeByParams(userId, lessonId, questionId, id);
            logger.info("Mistake deleted successfully for User ID: {}, Lesson ID: {}, Question ID: {}, Mistake ID: {}",
                    userId, lessonId, questionId, id);

            return ResponseEntity.ok(new ResponseObject("Mistake deleted successfully", HttpStatus.OK, null));
        } catch (DataNotFoundException e) {
            logger.error("Data not found while deleting mistake: User ID: {}, Lesson ID: {}, Question ID: {}, Mistake ID: {}",
                    userId, lessonId, questionId, id, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Mistake not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            logger.error("Unexpected error while deleting mistake", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error deleting mistake: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    // Exception handling
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> handleException(Exception ex) {
        logger.error("Internal server error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseObject("Internal server error: " + ex.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR, null));
    }
}
