package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.BookRequest;
import com.gorgeous.ringolift.responses.BookContentResponse;
import com.gorgeous.ringolift.responses.BookResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // Create a new book
    // POST http://localhost:8088/api/v1/books
    @PostMapping("")
    public ResponseEntity<ResponseObject> createBook(
            @Valid @RequestBody BookRequest bookRequest
    ) throws DataNotFoundException {
        BookResponse bookResponse = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Create book successfully")
                        .status(HttpStatus.CREATED)
                        .data(bookResponse)
                        .build()
        );
    }

    // Get all books
    // GET http://localhost:8088/api/v1/books
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllBooks() {
        List<BookResponse> bookResponses = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get list of books successfully")
                        .status(HttpStatus.OK)
                        .data(bookResponses)
                        .build()
        );
    }

    // Get a book by id
    // GET http://localhost:8080/api/v1/books/1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getBookById(
            @Valid @PathVariable("id") Long id
    ) throws DataNotFoundException {
        BookResponse bookResponse = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get book by id successfully")
                        .status(HttpStatus.OK)
                        .data(bookResponse)
                        .build()
        );
    }

    // Update a book by id
    // PUT http://localhost:8088/api/v1/books/1
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateBook(
            @Valid @PathVariable Long id,
            @Valid @RequestBody BookRequest bookRequest
    ) throws DataNotFoundException {
        BookResponse bookResponse = bookService.updateBook(id, bookRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Update book successfully")
                        .status(HttpStatus.OK)
                        .data(bookResponse)
                        .build()
        );
    }

    // Delete a book by id
    // DELETE http://localhost:8088/api/v1/books/1
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteBook(
            @Valid @PathVariable Long id
    ) throws DataNotFoundException {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Delete book successfully")
                        .status(HttpStatus.OK)
                        .build()
        );
    }

    // Read book content by content_url
    // GET http://localhost:8088/api/v1/books/content/{content_url}
    @GetMapping("/content/{content_url}")
    public ResponseEntity<ResponseObject> getBookByContentUrl(
            @Valid @PathVariable String content_url
    ) throws DataNotFoundException {
        BookContentResponse content = bookService.readBookContent(content_url);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Read book content successfully")
                        .status(HttpStatus.OK)
                        .data(content)
                        .build()
        );
    }
}
