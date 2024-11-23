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
public class LessonProgressRequest {
    @JsonProperty("correct_count")
    private Integer correctCount;

    @JsonProperty("incorrect_count")
    private Integer incorrectCount;

    @JsonProperty("time_spent")
    private Long timeSpent;

    @JsonProperty("lesson_id")
    private Long lessonId;

    @JsonProperty("user_id")
    private Long userId;
}
