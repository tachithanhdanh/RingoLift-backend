package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.FeedBackRequest;
import com.gorgeous.ringolift.responses.FeedBackResponse;

import java.util.List;

public interface FeedBackService {

    FeedBackResponse createFeedback(FeedBackRequest request) throws DataNotFoundException;

    FeedBackResponse getFeedback(Long id) throws DataNotFoundException;

    void deleteFeedback(Long feedbackId, Long userId, Long lessonId) throws DataNotFoundException;

    List<FeedBackResponse> getFeedbacksByUserAndLesson(Long userId, Long lessonId) throws DataNotFoundException;

    FeedBackResponse updateFeedback(Long feedbackId, FeedBackRequest request) throws DataNotFoundException;
}

