package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.TopicRequest;
import com.gorgeous.ringolift.responses.TopicResponse;

import java.util.List;

public interface TopicService {
    TopicResponse createTopic(TopicRequest request);
    TopicResponse getTopicById(Long id) throws DataNotFoundException;
    List<TopicResponse> getAllTopics();
    TopicResponse updateTopic(Long id, TopicRequest request) throws DataNotFoundException;
    void deleteTopic(Long id) throws DataNotFoundException;
}
