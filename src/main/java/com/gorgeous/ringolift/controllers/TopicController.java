package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.TopicRequest;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.responses.TopicResponse;
import com.gorgeous.ringolift.services.TopicService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping("")
    public ResponseEntity<ResponseObject> createTopic(@Valid @RequestBody TopicRequest request) {
        TopicResponse response = topicService.createTopic(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Create topic successfully")
                        .status(HttpStatus.CREATED)
                        .data(response)
                        .build()
        );
    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllTopics() {
        List<TopicResponse> responses = topicService.getAllTopics();
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get list of topics successfully")
                        .status(HttpStatus.OK)
                        .data(responses)
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getTopicById(@PathVariable Long id) throws DataNotFoundException {
        TopicResponse response = topicService.getTopicById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get topic by id successfully")
                        .status(HttpStatus.OK)
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateTopic(
            @PathVariable Long id,
            @Valid @RequestBody TopicRequest request
    ) throws DataNotFoundException {
        TopicResponse response = topicService.updateTopic(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Update topic successfully")
                        .status(HttpStatus.OK)
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteTopic(@PathVariable Long id) throws DataNotFoundException {
        topicService.deleteTopic(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Delete topic successfully")
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}
