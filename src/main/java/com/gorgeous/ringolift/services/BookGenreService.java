package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.BookGenreRequest;
import com.gorgeous.ringolift.responses.BookGenreResponse;

import java.util.List;

public interface BookGenreService {
    public BookGenreResponse createBookGenre(BookGenreRequest bookGenreRequest);
    public BookGenreResponse getBookGenreById(Long bookGenreId) throws DataNotFoundException;
    public List<BookGenreResponse> getAllBookGenres();
    public BookGenreResponse updateBookGenre(Long bookGenreId, BookGenreRequest bookGenreRequest) throws DataNotFoundException;
    public void deleteBookGenre(Long bookGenreId) throws DataNotFoundException;
}
