package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.ChapterRequest;
import com.gorgeous.ringolift.responses.ChapterResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.ChapterService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/chapters")
@RequiredArgsConstructor
public class ChapterController {

    private final ChapterService chapterService;

    // Create a new chapter
    // POST http://localhost:8088/api/v1/chapters
    @PostMapping("")
    public ResponseEntity<ResponseObject> createChapter(
            @Valid @RequestBody ChapterRequest chapterRequest,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            // Get all field error messages
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .message(String.join(";", errorMessages))
                            .status(HttpStatus.BAD_REQUEST)
                            .data(errorMessages)
                            .build());
        }
        ChapterResponse chapterResponse = chapterService.createChapter(chapterRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Chapter created successfully")
                        .status(HttpStatus.CREATED)
                        .data(chapterResponse)
                        .build());
    }

    // Get all chapters
    // GET http://localhost:8088/api/v1/chapters
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllChapters() {
        List<ChapterResponse> chapterResponses = chapterService.getAllChapters();
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Chapters retrieved successfully")
                        .status(HttpStatus.OK)
                        .data(chapterResponses)
                        .build());
    }

    // Get a chapter by id
    // GET http://localhost:8088/api/v1/chapters/1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getChapterById(@Valid @PathVariable Long id) throws DataNotFoundException {
        ChapterResponse chapterResponse = chapterService.getChapterById(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Chapter retrieved successfully")
                        .status(HttpStatus.OK)
                        .data(chapterResponse)
                        .build());
    }

    // Update a chapter by id
    // PUT http://localhost:8088/api/v1/chapters/1
    // admin job, will add @PreAuthorize("hasRole('ROLE_ADMIN')") later
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateChapter(
            @Valid @PathVariable Long id,
            @Valid @RequestBody ChapterRequest chapterRequest,
            BindingResult result
    ) throws DataNotFoundException {
        if (result.hasErrors()) {
            // Get all field error messages
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .message(String.join(";", errorMessages))
                            .status(HttpStatus.BAD_REQUEST)
                            .data(errorMessages)
                            .build());
        }
        ChapterResponse chapterResponse = chapterService.updateChapter(id, chapterRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Chapter updated successfully")
                        .status(HttpStatus.OK)
                        .data(chapterResponse)
                        .build());
    }

    // Delete a chapter by id
    // DELETE http://localhost:8088/api/v1/chapters/1
    // admin job, will add @PreAuthorize("hasRole('ROLE_ADMIN')") later
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteChapter(@Valid @PathVariable Long id) {
        chapterService.deleteChapter(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Chapter deleted successfully")
                        .status(HttpStatus.OK)
                        .data(null)
                        .build());
    }
}
