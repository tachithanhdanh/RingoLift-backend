package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.WordProgressRequest;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.responses.WordProgressResponse;
import com.gorgeous.ringolift.services.WordProgressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/word-progresses")
@RequiredArgsConstructor
public class WordProgressController {

    private final WordProgressService wordProgressService;

    // Create a word progress
    // POST http://localhost:8088/api/v1/word-progreses
    @PostMapping("")
    public ResponseEntity<ResponseObject> createWordProgress(
            @Valid @RequestBody WordProgressRequest wordProgressRequest
    ) throws DataNotFoundException {
        WordProgressResponse wordProgressResponse = wordProgressService.createWordProgress(wordProgressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Create word progress successfully")
                        .status(HttpStatus.CREATED)
                        .data(wordProgressResponse)
                        .build()
        );
    }

    // Get all word progresses
    // GET http://localhost:8088/api/v1/word-progresses
    @GetMapping("")
    public ResponseEntity<ResponseObject> getWordProgresses() {
        List<WordProgressResponse> wordProgressList = wordProgressService.getAllWordProgress();
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get list of word progress successfully")
                        .status(HttpStatus.OK)
                        .data(wordProgressList)
                        .build()
        );
    }

    // Get word progress by id
    // GET http://localhost:8088/api/v1/word-progresses/1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getWordProgress(
            @Valid @PathVariable Long id
    ) throws DataNotFoundException {
        WordProgressResponse wordProgressResponse = wordProgressService.getWordProgressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get word progress successfully")
                        .status(HttpStatus.OK)
                        .data(wordProgressResponse)
                        .build()
        );
    }

    // Update word progress
    // PUT http://localhost:8088/api/v1/word-progresses/1
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateWordProgress(
            @Valid @PathVariable Long id,
            @Valid @RequestBody WordProgressRequest wordProgressRequest
    ) throws DataNotFoundException {
        WordProgressResponse wordProgressResponse = wordProgressService.updateWordProgress(id, wordProgressRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Update word progress successfully")
                        .status(HttpStatus.OK)
                        .data(wordProgressResponse)
                        .build()
        );
    }

    // Delete word progress by id
    // DELETE http://localhost:8088/api/v1/word-progresses/1
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteWordProgress(
            @Valid @PathVariable Long id
    ) throws DataNotFoundException {
        wordProgressService.deleteWordProgress(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Delete word progress successfully")
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}
