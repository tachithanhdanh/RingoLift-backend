package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterProgressRequest {
    @JsonProperty("unlocked")
    private Boolean unlocked;

    @JsonProperty("chapter_id")
    private Long chapterId;

    @JsonProperty("user_id")
    private Long userId;
}
