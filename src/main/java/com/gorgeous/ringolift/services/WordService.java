package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.WordRequest;
import com.gorgeous.ringolift.responses.WordResponse;

import java.util.List;

public interface WordService {
    public WordResponse createWord(WordRequest wordRequest) throws DataNotFoundException;
    public WordResponse getWordById(Long wordId) throws DataNotFoundException;
    public List<WordResponse> getAllWords();
    public WordResponse updateWord(Long wordId, WordRequest wordRequest) throws DataNotFoundException;
    public void deleteWord(Long wordId) throws DataNotFoundException;
}
