package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.BookGenre;
import com.gorgeous.ringolift.repositories.BookGenreRepository;
import com.gorgeous.ringolift.requests.BookGenreRequest;
import com.gorgeous.ringolift.responses.BookGenreResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookGenreServiceImpl implements BookGenreService {

    private final BookGenreRepository bookGenreRepository;

    @Override
    public BookGenreResponse createBookGenre(BookGenreRequest bookGenreRequest) {
        // Create book genre
        BookGenre bookGenre = BookGenre.builder()
                .genreType(bookGenreRequest.getGenreType())
                .build();
        return BookGenreResponse.fromBookGenre(bookGenreRepository.save(bookGenre));
    }

    @Override
    public BookGenreResponse getBookGenreById(Long bookGenreId) throws DataNotFoundException {
        return bookGenreRepository.findById(bookGenreId)
                .map(BookGenreResponse::fromBookGenre)
                .orElseThrow(() -> new DataNotFoundException("BookGenre not found"));

    }

    @Override
    public List<BookGenreResponse> getAllBookGenres() {
        return bookGenreRepository.findAll().stream().map(BookGenreResponse::fromBookGenre).toList();
    }

    @Override
    public BookGenreResponse updateBookGenre(Long bookGenreId, BookGenreRequest bookGenreRequest) throws DataNotFoundException {
        BookGenre bookGenre = bookGenreRepository.findById(bookGenreId).orElseThrow(() -> new DataNotFoundException("BookGenre not found"));
        bookGenre.setGenreType(bookGenreRequest.getGenreType());
        return BookGenreResponse.fromBookGenre(bookGenreRepository.save(bookGenre));
    }

    @Override
    public void deleteBookGenre(Long bookGenreId) throws DataNotFoundException {
        if (!bookGenreRepository.existsById(bookGenreId)) {
            throw new DataNotFoundException("BookGenre not found");
        }
        bookGenreRepository.deleteById(bookGenreId);
    }
}
