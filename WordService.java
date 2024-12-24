package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.WordRequest;
import com.gorgeous.ringolift.responses.WordResponse;

import java.util.List;

public interface WordService {
    WordResponse createWord(WordRequest wordRequest) throws DataNotFoundException;
    WordResponse getWordById(Long wordId) throws DataNotFoundException;
    List<WordResponse> getAllWords();
    WordResponse updateWord(Long wordId, WordRequest wordRequest) throws DataNotFoundException;
    void deleteWord(Long wordId) throws DataNotFoundException;

    // Thêm phương thức lấy tất cả words theo topic
    List<WordResponse> getWordsByTopicId(Long topicId) throws DataNotFoundException;
}
