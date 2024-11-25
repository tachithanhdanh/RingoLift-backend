package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.WordProgressRequest;
import com.gorgeous.ringolift.responses.WordProgressResponse;

import java.util.List;

public interface WordProgressService {
    WordProgressResponse createWordProgress(WordProgressRequest wordProgressRequest) throws DataNotFoundException;
    WordProgressResponse getWordProgressById(Long wordProgressId) throws DataNotFoundException;
    List<WordProgressResponse> getAllWordProgress();
    WordProgressResponse updateWordProgress(Long wordProgressId, WordProgressRequest wordProgressRequest) throws DataNotFoundException;
    void deleteWordProgress(Long wordProgressId) throws DataNotFoundException;
}
