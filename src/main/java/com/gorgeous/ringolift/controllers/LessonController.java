package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.requests.LessonRequest;
import com.gorgeous.ringolift.responses.LessonQuestionResponse;
import com.gorgeous.ringolift.responses.LessonResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.LessonService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    // Create a new lesson
    // POST http://localhost:8088/api/v1/lessons
    @PostMapping("")
    public ResponseEntity<ResponseObject> createLesson(
            @Valid @RequestBody LessonRequest lessonRequest
    ) throws DataNotFoundException {
        LessonResponse lessonResponse = lessonService.createLesson(lessonRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Lesson created successfully")
                        .status(HttpStatus.CREATED)
                        .data(lessonResponse)
                        .build());
    }

    // Get all lessons by chapter id
    // GET http://localhost:8088/api/v1/lessons/chapter/1
    @GetMapping("chapter/{chapter_id}")
    public ResponseEntity<ResponseObject> getAllLessonsByChapterId(
            @Valid @PathVariable("chapter_id") Long chapterId
    ) {
        List<LessonResponse> lessonResponses = lessonService.getLessonsByChapterId(chapterId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get list of lessons by chapter id successfully")
                        .status(HttpStatus.OK)
                        .data(lessonResponses)
                        .build());
    }

    // Get a lesson by id
    // GET http://localhost:8088/api/v1/lessons/1
    @GetMapping("/{lesson_id}")
    public ResponseEntity<ResponseObject> getLessonById(
            @Valid @PathVariable("lesson_id") Long lessonId
    ) throws DataNotFoundException {
        LessonResponse lessonResponse = lessonService.getLessonById(lessonId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get lesson by id successfully")
                        .status(HttpStatus.OK)
                        .data(lessonResponse)
                        .build());
    }

    // Update a lesson by id
    // PUT http://localhost:8088/api/v1/lessons/1
    @PutMapping("/{lesson_id}")
    public ResponseEntity<ResponseObject> updateLesson(
            @Valid @PathVariable("lesson_id") Long lessonId,
            @Valid @RequestBody LessonRequest lessonRequest
    ) throws DataNotFoundException {
        LessonResponse lessonResponse = lessonService.updateLesson(lessonId, lessonRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Lesson updated successfully")
                        .status(HttpStatus.OK)
                        .data(lessonResponse)
                        .build());
    }

    // Delete a lesson by id
    // DELETE http://localhost:8088/api/v1/lessons/1
    @DeleteMapping("/{lesson_id}")
    public ResponseEntity<ResponseObject> deleteLesson(
            @Valid @PathVariable("lesson_id") Long lessonId
    ) throws DataNotFoundException {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Lesson deleted successfully")
                        .data(null)
                        .status(HttpStatus.OK)
                        .build());
    }

    // Add a question to a lesson
    // POST http://localhost:8088/api/v1/lessons/1/questions/1
    @PostMapping("/{lesson_id}/questions/{question_id}")
    public ResponseEntity<ResponseObject> addQuestionToLesson(
            @Valid @PathVariable("lesson_id") Long lessonId,
            @Valid @PathVariable("question_id") Long questionId
    ) throws DataNotFoundException, DuplicateDataException {
        LessonQuestionResponse lessonResponse = lessonService.addQuestionToLesson(lessonId,
                questionId);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Question added to lesson successfully")
                        .status(HttpStatus.CREATED)
                        .data(lessonResponse)
                        .build());
    }

    // Get all questions by lesson id
    // GET http://localhost:8088/api/v1/lessons/1/questions
    @GetMapping("/{lesson_id}/questions")
    public ResponseEntity<ResponseObject> getQuestionsByLessonId(
            @Valid @PathVariable("lesson_id") Long lessonId
    ) {
        List<LessonQuestionResponse> lessonResponses = lessonService.getQuestionsByLessonId(
                lessonId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get list of questions by lesson id successfully")
                        .status(HttpStatus.OK)
                        .data(lessonResponses)
                        .build());
    }

    // Get a lesson question by lesson id and question id
    // GET http://localhost:8088/api/v1/lessons/1/questions/1
    @GetMapping("/{lesson_id}/questions/{question_id}")
    public ResponseEntity<ResponseObject> getLessonQuestionById(
            @Valid @PathVariable("lesson_id") Long lessonId,
            @Valid @PathVariable("question_id") Long questionId
    ) throws DataNotFoundException {
        LessonQuestionResponse lessonResponse = lessonService.getLessonQuestionByLessonIdAndQuestionId(
                lessonId, questionId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get lesson question by lesson id and question id successfully")
                        .status(HttpStatus.OK)
                        .data(lessonResponse)
                        .build());
    }

    // Remove a question from a lesson
    // DELETE http://localhost:8088/api/v1/lessons/1/questions/1
    @DeleteMapping("/{lesson_id}/questions/{question_id}")
    public ResponseEntity<ResponseObject> removeQuestionFromLesson(
            @Valid @PathVariable("lesson_id") Long lessonId,
            @Valid @PathVariable("question_id") Long questionId
    ) throws DataNotFoundException {
        lessonService.removeQuestionFromLesson(lessonId, questionId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Question removed from lesson successfully")
                        .data(null)
                        .status(HttpStatus.OK)
                        .build());
    }

    // Remove all questions from a lesson
    // DELETE http://localhost:8088/api/v1/lessons/1/questions
    @DeleteMapping("/{lesson_id}/questions")
    public ResponseEntity<ResponseObject> removeAllQuestionsFromLesson(
            @Valid @PathVariable("lesson_id") Long lessonId
    ) throws DataNotFoundException {
        lessonService.removeAllQuestionsFromLesson(lessonId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("All questions removed from lesson successfully")
                        .data(null)
                        .status(HttpStatus.OK)
                        .build());
    }
}
