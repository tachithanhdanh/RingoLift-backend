package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.BookGenre;
import com.gorgeous.ringolift.requests.BookGenreRequest;
import com.gorgeous.ringolift.responses.BookGenreResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.BookGenreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/book-genres")
@RequiredArgsConstructor
public class BookGenreController {

    private final BookGenreService bookGenreService;

    // Create a new book genre
    // POST http://localhost:8088/api/v1/book-genres
    @PostMapping("")
    public ResponseEntity<ResponseObject> createBookGenre(
            @Valid @RequestBody BookGenreRequest bookGenreRequest
    ) {
        BookGenreResponse bookGenreResponse = bookGenreService.createBookGenre(bookGenreRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Create book genre successfully")
                        .status(HttpStatus.CREATED)
                        .data(bookGenreResponse)
                        .build()
        );
    }

    // Get all book genres
    // Get http://localhost:8088/api/v1/book-genres
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllBookGenres() {
        List<BookGenreResponse> bookGenreResponses = bookGenreService.getAllBookGenres();
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get list of book genres successfully")
                        .status(HttpStatus.OK)
                        .data(bookGenreResponses)
                        .build()
        );
    }

    // Get a book genre by id
    // GET http://localhost:8088/api/v1/book-genres/1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getBookGenreById(
            @PathVariable("id") Long id
    ) throws DataNotFoundException {
        BookGenreResponse bookGenreResponse = bookGenreService.getBookGenreById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get book genre by id successfully")
                        .status(HttpStatus.OK)
                        .data(bookGenreResponse)
                        .build()
        );
    }

    // Update a book genre by id
    // PUT http://localhost:8088/api/v1/books-genres/1
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateBookGenre(
            @Valid @PathVariable Long id,
            @Valid @RequestBody BookGenreRequest bookGenreRequest
    ) throws DataNotFoundException {
        BookGenreResponse bookGenreResponse = bookGenreService.updateBookGenre(id, bookGenreRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Update book genre successfully")
                        .status(HttpStatus.OK)
                        .data(bookGenreResponse)
                        .build()
        );
    }

    // Delete a book genre by id
    // DELETE http://localhost:8088/api/v1/book-genres/1
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteBookGenre(
            @PathVariable Long id
    ) throws DataNotFoundException {
        bookGenreService.deleteBookGenre(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Delete book genre successfully")
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}
