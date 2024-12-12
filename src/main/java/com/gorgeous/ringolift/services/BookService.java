package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.BookRequest;
import com.gorgeous.ringolift.responses.BookContentResponse;
import com.gorgeous.ringolift.responses.BookResponse;

import java.util.List;

public interface BookService {
    BookResponse createBook(BookRequest bookRequest) throws DataNotFoundException;
    BookResponse getBookById(Long bookId) throws DataNotFoundException;
    List<BookResponse> getAllBooks();
    BookResponse updateBook(Long bookId, BookRequest bookRequest) throws DataNotFoundException;
    void deleteBook(Long bookId) throws DataNotFoundException;
    BookContentResponse readBookContent(String contentUrl) throws DataNotFoundException;
}
