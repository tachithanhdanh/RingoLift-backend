package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Book;
import com.gorgeous.ringolift.models.BookGenre;
import com.gorgeous.ringolift.repositories.BookGenreRepository;
import com.gorgeous.ringolift.repositories.BookRepository;
import com.gorgeous.ringolift.requests.BookRequest;
import com.gorgeous.ringolift.responses.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookGenreRepository bookGenreRepository;

    @Override
    public BookResponse createBook(BookRequest bookRequest) throws DataNotFoundException {
        // Get book genre
        BookGenre bookGenre = bookGenreRepository.findById(bookRequest.getGenreId())
                .orElseThrow(() -> new DataNotFoundException("Genre not found"));

        // Create book
        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .genre(bookGenre)
                .publishedDate(bookRequest.getPublishedDate())
                .isbn(bookRequest.getIsbn())
                .numOfPages(bookRequest.getNumOfPages())
                .publisher(bookRequest.getPublisher())
                .description(bookRequest.getDescription())
                .coverImage(bookRequest.getCoverImage())
                .contentUrl(bookRequest.getContentUrl())
                .build();

        return BookResponse.fromBook(bookRepository.save(book));
    }

    @Override
    public BookResponse getBookById(Long bookId) throws DataNotFoundException {
        return bookRepository.findById(bookId)
                .map(BookResponse::fromBook)
                .orElseThrow(() -> new DataNotFoundException("Book not found"));
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream().map(BookResponse::fromBook).toList();
    }

    @Override
    public BookResponse updateBook(Long bookId, BookRequest bookRequest) throws DataNotFoundException {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new DataNotFoundException("Book not found"));
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setGenre(bookGenreRepository.findById(bookRequest.getGenreId()).orElseThrow(() -> new DataNotFoundException("Genre not found")));
        book.setPublishedDate(bookRequest.getPublishedDate());
        book.setIsbn(bookRequest.getIsbn());
        book.setNumOfPages(bookRequest.getNumOfPages());
        book.setPublisher(bookRequest.getPublisher());
        book.setDescription(bookRequest.getDescription());
        book.setCoverImage(bookRequest.getCoverImage());
        book.setContentUrl(bookRequest.getContentUrl());
        return BookResponse.fromBook(bookRepository.save(book));
    }

    @Override
    public void deleteBook(Long bookId) throws DataNotFoundException {
        if (!bookRepository.existsById(bookId)) {
            throw new DataNotFoundException("Book not found");
        }
        bookRepository.deleteById(bookId);
    }
}
