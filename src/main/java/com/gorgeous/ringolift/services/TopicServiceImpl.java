package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Topic;
import com.gorgeous.ringolift.repositories.TopicRepository;
import com.gorgeous.ringolift.requests.TopicRequest;
import com.gorgeous.ringolift.responses.TopicResponse;
import com.gorgeous.ringolift.services.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    @Override
    public TopicResponse createTopic(TopicRequest request) {
        Topic topic = Topic.builder()
                .name(request.getName())
                .build();
        topic.setCreatedAt(LocalDateTime.now());
        topic.setUpdatedAt(LocalDateTime.now());
        topic = topicRepository.save(topic);
        return TopicResponse.fromTopic(topic);
    }

    @Override
    public TopicResponse getTopicById(Long id) throws DataNotFoundException {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Topic not found"));
        return TopicResponse.fromTopic(topic);
    }

    @Override
    public List<TopicResponse> getAllTopics() {
        return topicRepository.findAll()
                .stream().map(TopicResponse::fromTopic).toList();
    }

    @Override
    public TopicResponse updateTopic(Long id, TopicRequest request) throws DataNotFoundException {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Topic not found"));
        topic.setName(request.getName());
        topic.setUpdatedAt(LocalDateTime.now());
        topic = topicRepository.save(topic);
        return TopicResponse.fromTopic(topic);
    }

    @Override
    public void deleteTopic(Long id) throws DataNotFoundException {
        Topic topic = topicRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Topic not found"));
        topicRepository.delete(topic);
    }
}
