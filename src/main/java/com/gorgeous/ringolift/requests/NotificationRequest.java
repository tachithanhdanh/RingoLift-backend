package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("type_id")
    private Long typeId;

    @JsonProperty("content")
    private String content;
}
