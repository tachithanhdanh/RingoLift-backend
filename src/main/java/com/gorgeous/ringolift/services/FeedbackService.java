package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.FeedbackRequest;
import com.gorgeous.ringolift.responses.FeedbackResponse;

import java.util.List;

public interface FeedbackService {

    FeedbackResponse createFeedback(FeedbackRequest request) throws DataNotFoundException;

    FeedbackResponse getFeedback(Long id) throws DataNotFoundException;

    void deleteFeedback(Long feedbackId, Long userId, Long lessonId) throws DataNotFoundException;

    List<FeedbackResponse> getFeedbacksByUserAndLesson(Long userId, Long lessonId) throws DataNotFoundException;

    FeedbackResponse updateFeedback(Long feedbackId, FeedbackRequest request) throws DataNotFoundException;
}

