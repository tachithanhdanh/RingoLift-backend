package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Word;
import com.gorgeous.ringolift.requests.WordRequest;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.responses.WordResponse;
import com.gorgeous.ringolift.services.WordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/words")
@RequiredArgsConstructor
public class WordController {

    private final WordService wordService;

    // Create a new word
    // POST http://localhost:8088/api/v1/books
    @PostMapping("")
    public ResponseEntity<ResponseObject> addWord(
        @Valid @RequestBody WordRequest wordRequest
    ) throws DataNotFoundException {
        WordResponse wordResponse = wordService.createWord(wordRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Create word successfully")
                        .status(HttpStatus.CREATED)
                        .data(wordResponse)
                        .build()
        );
    }

    // Get all words
    // GET http://localhost:8088/api/v1/words
    @GetMapping("")
    public ResponseEntity<ResponseObject> getWords() {
        List<WordResponse> wordResponses = wordService.getAllWords();
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get list of words successfully")
                        .status(HttpStatus.OK)
                        .data(wordResponses)
                        .build()
        );
    }

    // Get a word by id
    // GET http://localhost:8088/api/v1/words/1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getWordBy(
            @Valid @PathVariable Long id
    ) throws DataNotFoundException {
        WordResponse wordResponse = wordService.getWordById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get word by id successfully")
                        .status(HttpStatus.OK)
                        .data(wordResponse)
                        .build()
        );
    }

    // Update a word by id
    // PUT http://localhost:8088/api/v1/words/1
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateWord(
            @Valid @PathVariable Long id,
            @Valid @RequestBody WordRequest wordRequest
    ) throws DataNotFoundException {
        WordResponse wordResponse = wordService.updateWord(id, wordRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Update word successfully")
                        .status(HttpStatus.OK)
                        .data(wordResponse)
                        .build()
        );
    }

    // Delete a word by id
    // DELETE http://localhost:8088/api/v1/words/1
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteWord(
            @Valid @PathVariable Long id
    ) throws DataNotFoundException {
        wordService.deleteWord(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Delete word successfully")
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}
