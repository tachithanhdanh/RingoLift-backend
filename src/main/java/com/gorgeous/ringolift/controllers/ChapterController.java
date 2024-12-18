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
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> createChapter(
            @Valid @RequestBody ChapterRequest chapterRequest
    ) {
        ChapterResponse chapterResponse = chapterService.createChapter(chapterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Create chapter successfully")
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
                        .message("Get list of chapters successfully")
                        .status(HttpStatus.OK)
                        .data(chapterResponses)
                        .build());
    }

    // Get a chapter by id
    // GET http://localhost:8088/api/v1/chapters/1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getChapterById(@Valid @PathVariable Long id)
            throws DataNotFoundException {
        ChapterResponse chapterResponse = chapterService.getChapterById(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get chapter by id successfully")
                        .status(HttpStatus.OK)
                        .data(chapterResponse)
                        .build());
    }

    // Update a chapter by id
    // PUT http://localhost:8088/api/v1/chapters/1
    // admin job, will add @PreAuthorize("hasRole('ROLE_ADMIN')") later
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> updateChapter(
            @Valid @PathVariable Long id,
            @Valid @RequestBody ChapterRequest chapterRequest
    ) throws DataNotFoundException {
        ChapterResponse chapterResponse = chapterService.updateChapter(id, chapterRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Update chapter successfully")
                        .status(HttpStatus.OK)
                        .data(chapterResponse)
                        .build());
    }

    // Delete a chapter by id
    // DELETE http://localhost:8088/api/v1/chapters/1
    // admin job, will add @PreAuthorize("hasRole('ROLE_ADMIN')") later
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseObject> deleteChapter(@Valid @PathVariable Long id)
            throws DataNotFoundException {
        chapterService.deleteChapter(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Delete chapter successfully")
                        .status(HttpStatus.OK)
                        .data(null)
                        .build());
    }
}
