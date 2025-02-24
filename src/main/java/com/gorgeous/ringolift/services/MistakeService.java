package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.MistakeRequest;
import com.gorgeous.ringolift.responses.MistakeResponse;

import java.util.List;

public interface MistakeService {
    MistakeResponse createMistake(MistakeRequest request) throws DataNotFoundException;

    MistakeResponse updateMistake(Long id, MistakeRequest request) throws DataNotFoundException;

    void deleteMistakeByParams(Long userId, Long lessonId, Long questionId, Long id) throws DataNotFoundException;

    MistakeResponse getMistakeById(Long userId, Long lessonId, Long questionId, Long id) throws DataNotFoundException;

    List<MistakeResponse> getMistakesByLessonAndUserId(Long userId, Long lessonId) throws DataNotFoundException;

    List<MistakeResponse> getAllMistakes();
}
