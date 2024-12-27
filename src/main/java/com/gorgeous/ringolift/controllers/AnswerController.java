package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.AnswerRequest;
import com.gorgeous.ringolift.responses.AnswerResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.AnswerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/answers")
@CrossOrigin(origins = "*")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    /**
     * Create a new answer.
     * POST /api/v1/answers
     */
    @PostMapping
    public ResponseEntity<ResponseObject> createAnswer(@Valid @RequestBody AnswerRequest request) {
        try {
            // Attempt to create answer
            AnswerResponse response = answerService.createAnswer(request);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseObject("Answer created successfully", HttpStatus.CREATED, response));
        } catch (Exception e) {
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

        try {
            // Validate if the questionId is provided in the request body
            if (request.getQuestionId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject("Question ID cannot be null", HttpStatus.BAD_REQUEST, null));
            }

            // Proceed with the update logic using the answerId from the path and questionId from the body
            AnswerResponse response = answerService.updateAnswer(answerId, request.getQuestionId(), request);

            return ResponseEntity.ok(new ResponseObject("Answer updated successfully", HttpStatus.OK, response));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Answer or Question not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
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

        try {
            var responses = answerService.getAnswersByQuestionId(questionId);
            if (responses.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseObject("No answers found for this question", HttpStatus.NOT_FOUND, null));
            }

            return ResponseEntity.ok(new ResponseObject("Answers fetched successfully", HttpStatus.OK, responses));
        } catch (Exception e) {
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

        try {
            answerService.deleteAnswer(questionId, answerId);
            return ResponseEntity.ok(new ResponseObject("Answer deleted successfully", HttpStatus.OK, null));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Answer or Question not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error deleting answer: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }
}
