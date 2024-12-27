package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.QuestionRequest;
import com.gorgeous.ringolift.requests.QuestionTypeRequest;
import com.gorgeous.ringolift.responses.QuestionResponse;
import com.gorgeous.ringolift.responses.QuestionTypeResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    // Create a new question
    // POST localhost:8088/api/v1/questions
    @PostMapping("")
    public ResponseEntity<ResponseObject> createQuestion(
            @Valid @RequestBody QuestionRequest questionRequest) throws DataNotFoundException {
        QuestionResponse questionResponse = questionService.createQuestion(questionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseObject.builder()
                .message("Question created successfully")
                .data(questionResponse)
                .status(HttpStatus.CREATED)
                .build());
    }

    // Get all questions
    // GET localhost:8088/api/v1/questions
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllQuestions() {
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get list of questions successfully")
                        .data(questionService.getAllQuestions())
                        .status(HttpStatus.OK)
                        .build());
    }

    // Get question by id
    // GET localhost:8088/api/v1/questions/1
    @GetMapping("/{question_id}")
    public ResponseEntity<ResponseObject> getQuestionById(
            @Valid @PathVariable("question_id") Long questionId)
            throws DataNotFoundException {
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get question by id successfully")
                        .data(questionService.getQuestionById(questionId))
                        .status(HttpStatus.OK)
                        .build());
    }

    // Get questions by type
    // GET localhost:8088/api/v1/questions/by-type/type_id
    @GetMapping("/by-type/{type_id}")
    public ResponseEntity<ResponseObject> getQuestionsByType(
            @Valid @PathVariable("type_id") Long typeId) throws DataNotFoundException {
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get list of questions by type successfully")
                        .data(questionService.getQuestionsByTypeId(typeId))
                        .status(HttpStatus.OK)
                        .build());
    }

    // Update question by id
    // PUT localhost:8088/api/v1/questions/1
    @PutMapping("/{question_id}")
    public ResponseEntity<ResponseObject> updateQuestion(
            @Valid @PathVariable("question_id") Long questionId,
            @Valid @RequestBody QuestionRequest questionRequest)
            throws DataNotFoundException {
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Question updated successfully")
                        .data(questionService.updateQuestion(questionId, questionRequest))
                        .status(HttpStatus.OK)
                        .build());
    }

    // Delete question by id
    // DELETE localhost:8088/api/v1/questions/1
    @DeleteMapping("/{question_id}")
    public ResponseEntity<ResponseObject> deleteQuestion(
            @Valid @PathVariable("question_id") Long questionId) throws DataNotFoundException {
        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Question deleted successfully")
                        .status(HttpStatus.OK)
                        .build());
    }

    // Create a new question type
    // POST localhost:8088/api/v1/questions/types
    // Only admin can create a new question type
//    @PostMapping("/types")
    public ResponseEntity<ResponseObject> createQuestionType(
            @Valid @RequestBody QuestionTypeRequest questionTypeRequest) {
        QuestionTypeResponse questionTypeResponse = questionService.createQuestionType(questionTypeRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseObject.builder()
                .message("Question type created successfully")
                .data(questionTypeResponse)
                .status(HttpStatus.CREATED)
                .build());
    }

    // Get all question types
    // GET localhost:8088/api/v1/questions/types
    @GetMapping("/types")
    public ResponseEntity<ResponseObject> getAllQuestionTypes() {
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get list of question types successfully")
                        .data(questionService.getAllQuestionTypes())
                        .status(HttpStatus.OK)
                        .build());
    }

    // Get question type by id
    // GET localhost:8088/api/v1/questions/types/1
    @GetMapping("/types/{type_id}")
    public ResponseEntity<ResponseObject> getQuestionTypeById(
            @Valid @PathVariable("type_id") Long typeId) throws DataNotFoundException {
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get question type by id successfully")
                        .data(questionService.getQuestionTypeById(typeId))
                        .status(HttpStatus.OK)
                        .build());
    }

    // Update question type by id
    // PUT localhost:8088/api/v1/questions/types/1
    // Only admin can update a question type
//    @PutMapping("/types/{type_id}")
    public ResponseEntity<ResponseObject> updateQuestionType(
            @Valid @PathVariable("type_id") Long typeId,
            @Valid @RequestBody QuestionTypeRequest questionTypeRequest)
            throws DataNotFoundException {
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Question type updated successfully")
                        .data(questionService.updateQuestionType(typeId, questionTypeRequest))
                        .status(HttpStatus.OK)
                        .build());
    }

    // Delete question type by id
    // DELETE localhost:8088/api/v1/questions/types/1
    // Only admin can delete a question type
//    @DeleteMapping("/types/{type_id}")
    public ResponseEntity<ResponseObject> deleteQuestionType(
            @Valid @PathVariable("type_id") Long typeId) throws DataNotFoundException {
        questionService.deleteQuestionType(typeId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Question type deleted successfully")
                        .status(HttpStatus.OK)
                        .build());
    }

    // Get lessons by question id
    // GET localhost:8088/api/v1/questions/1/lessons
    @GetMapping("/{question_id}/lessons")
    public ResponseEntity<ResponseObject> getLessonsByQuestionId(
            @Valid @PathVariable("question_id") Long questionId) {
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get list of lessons by question id successfully")
                        .data(questionService.getLessonsByQuestionId(questionId))
                        .status(HttpStatus.OK)
                        .build());
    }

    // Remove all lessons from question
    // DELETE localhost:8088/api/v1/questions/1/lessons
    @DeleteMapping("/{question_id}/lessons")
    public ResponseEntity<ResponseObject> removeAllLessonsFromQuestion(
            @Valid @PathVariable("question_id") Long questionId) throws DataNotFoundException {
        questionService.removeAllLessonsFromQuestion(questionId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("All lessons removed from question successfully")
                        .status(HttpStatus.OK)
                        .build());
    }
}
