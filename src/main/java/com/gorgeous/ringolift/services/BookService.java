package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.BookRequest;
import com.gorgeous.ringolift.responses.BookResponse;
import java.util.List;

public interface BookService {
    public BookResponse createBook(BookRequest bookRequest) throws DataNotFoundException;
    public BookResponse getBookById(Long bookId) throws DataNotFoundException;
    public List<BookResponse> getAllBooks();
    public BookResponse updateBook(Long bookId, BookRequest bookRequest) throws DataNotFoundException;
    public void deleteBook(Long bookId) throws DataNotFoundException;
}
