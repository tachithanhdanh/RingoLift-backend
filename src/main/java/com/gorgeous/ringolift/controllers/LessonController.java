package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.LessonRequest;
import com.gorgeous.ringolift.responses.LessonResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gorgeous.ringolift.services.LessonService;

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
        return ResponseEntity.ok(
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
    ) {
        lessonService.deleteLesson(lessonId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Lesson deleted successfully")
                        .status(HttpStatus.OK)
                        .build());
    }
}
