package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Question;
import com.gorgeous.ringolift.requests.UserAnswerRequest;
import com.gorgeous.ringolift.responses.UserAnswerResponse;

import java.util.List;

public interface UserAnswerService {
    UserAnswerResponse createUserAnswer(UserAnswerRequest request) throws DataNotFoundException;

    UserAnswerResponse updateUserAnswer(Long id, UserAnswerRequest request) throws DataNotFoundException;

    void deleteUserAnswer(Long id) throws DataNotFoundException;

    UserAnswerResponse getUserAnswerById(Long id) throws DataNotFoundException;

    List<UserAnswerResponse> getUserAnswersByUserId(Long userId) throws DataNotFoundException;

    List<UserAnswerResponse> getUserAnswersByQuestionId(Long questionId) throws DataNotFoundException;

    Question getQuestionByUserAnswerId(Long userAnswerId) throws DataNotFoundException;
}

