package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.AnswerRequest;
import com.gorgeous.ringolift.responses.AnswerResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/answers")
@CrossOrigin(origins = "*")
public class AnswerController {

    private static final Logger logger = LoggerFactory.getLogger(AnswerController.class);

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
        logger.info("AnswerController initialized");
    }

    /**
     * Create a new answer.
     * POST /api/v1/answers
     */
    @PostMapping
    public ResponseEntity<ResponseObject> createAnswer(@Valid @RequestBody AnswerRequest request) {
        logger.info("Received create answer request - Request: {}", request);

        try {
            // Attempt to create answer
            AnswerResponse response = answerService.createAnswer(request);
            logger.info("Answer created successfully with ID: {}", response.getId());

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseObject("Answer created successfully", HttpStatus.CREATED, response));
        } catch (Exception e) {
            logger.error("Unexpected error while creating answer", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error creating answer: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Update an existing answer based on answer ID and question ID.
     * PUT /api/v1/answers/{id}
     */
    @PutMapping("/{answerId}")
    public ResponseEntity<ResponseObject> updateAnswer(
            @PathVariable Long answerId,   // Only answerId comes from the path
            @Valid @RequestBody AnswerRequest request) {  // Include questionId in the request body
        logger.info("Received update answer request - Answer ID: {}, Request: {}", answerId, request);

        try {
            // Validate if the questionId is provided in the request body
            if (request.getQuestionId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject("Question ID cannot be null", HttpStatus.BAD_REQUEST, null));
            }

            // Proceed with the update logic using the answerId from the path and questionId from the body
            AnswerResponse response = answerService.updateAnswer(answerId, request.getQuestionId(), request);
            logger.info("Answer updated successfully with ID: {} for Question ID: {}", response.getId(), request.getQuestionId());

            return ResponseEntity.ok(new ResponseObject("Answer updated successfully", HttpStatus.OK, response));
        } catch (DataNotFoundException e) {
            logger.error("Data not found while updating answer: Answer ID: {}, Question ID: {}", answerId, request.getQuestionId(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Answer or Question not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            logger.error("Unexpected error while updating answer: Answer ID: {}, Question ID: {}", answerId, request.getQuestionId(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error updating answer: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }


    /**
     * Retrieve all answers for a specific question.
     * GET /api/v1/answers/{questionId}
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<ResponseObject> getAnswersByQuestionId(@PathVariable Long questionId) {
        logger.info("Retrieving answers for Question ID: {}", questionId);

        try {
            var responses = answerService.getAnswersByQuestionId(questionId);
            if (responses.isEmpty()) {
                logger.info("No answers found for Question ID: {}", questionId);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("No answers found for this question", HttpStatus.NOT_FOUND, null));
            }
            logger.info("Found {} answers for Question ID: {}", responses.size(), questionId);

            return ResponseEntity.ok(new ResponseObject("Answers fetched successfully", HttpStatus.OK, responses));
        } catch (Exception e) {
            logger.error("Unexpected error while fetching answers for Question ID: {}", questionId, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error fetching answers: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Retrieve answers for a specific question filtered by correct/incorrect status.
     * GET /api/v1/answers/{questionId}/{isCorrect}
     */
    @GetMapping("/{questionId}/{isCorrect}")
    public ResponseEntity<ResponseObject> getAnswersByQuestionIdAndStatus(
            @PathVariable Long questionId, @PathVariable boolean isCorrect) {
        logger.info("Retrieving answers for Question ID: {} with correctness: {}", questionId, isCorrect);

        try {
            var responses = answerService.getAnswersByQuestionIdAndStatus(questionId, isCorrect);
            if (responses.isEmpty()) {
                logger.info("No answers found for Question ID: {} with correctness: {}", questionId, isCorrect);
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("No answers found for this question with the specified correctness",
                                HttpStatus.NOT_FOUND, null));
            }
            logger.info("Found {} answers for Question ID: {} with correctness: {}", responses.size(), questionId, isCorrect);

            return ResponseEntity.ok(new ResponseObject("Answers fetched successfully", HttpStatus.OK, responses));
        } catch (Exception e) {
            logger.error("Unexpected error while fetching answers for Question ID: {} with correctness: {}", questionId, isCorrect, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error fetching answers: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Delete an answer by question ID and answer ID.
     * DELETE /api/v1/answers/{questionId}/{answerId}
     */
    @DeleteMapping("/{questionId}/{answerId}")
    public ResponseEntity<ResponseObject> deleteAnswer(
            @PathVariable Long questionId, @PathVariable Long answerId) {
        logger.info("Deleting answer with Answer ID: {} for Question ID: {}", answerId, questionId);

        try {
            answerService.deleteAnswer(questionId, answerId);
            logger.info("Answer deleted successfully with Answer ID: {} for Question ID: {}", answerId, questionId);

            return ResponseEntity.ok(new ResponseObject("Answer deleted successfully", HttpStatus.OK, null));
        } catch (DataNotFoundException e) {
            logger.error("Data not found while deleting answer: Answer ID: {}, Question ID: {}", answerId, questionId, e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Answer or Question not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            logger.error("Unexpected error while deleting answer", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error deleting answer: " + e.getMessage(),
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
