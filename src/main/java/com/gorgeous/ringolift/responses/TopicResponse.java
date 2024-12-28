package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.Topic;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TopicResponse extends BaseResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    public static TopicResponse fromTopic(Topic topic) {
        TopicResponse response = TopicResponse.builder()
                .id(topic.getId())
                .name(topic.getName())
                .build();
        response.setCreatedAt(topic.getCreatedAt());
        response.setUpdatedAt(topic.getUpdatedAt());
        return response;
    }
}
