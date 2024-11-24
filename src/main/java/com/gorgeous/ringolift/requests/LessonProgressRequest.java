package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "correct_count is required")
    private Integer correctCount;

    @JsonProperty("incorrect_count")
    @NotNull(message = "incorrect_count is required")
    private Integer incorrectCount;

    @JsonProperty("time_spent")
    @NotNull(message = "time_spent is required")
    private Long timeSpent;

    @JsonProperty("lesson_id")
//    @NotNull(message = "lesson_id is required")
    private Long lessonId;

    @JsonProperty("user_id")
//    @NotNull(message = "user_id is required")
    private Long userId;
}
