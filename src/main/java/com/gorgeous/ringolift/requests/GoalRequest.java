package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoalRequest {
    @JsonProperty("time_spent")
    @PositiveOrZero(message = "Time spent must not be negative")
    private Integer timeSpent;

    @JsonProperty("lesson_count")
    @PositiveOrZero(message = "Lesson count must not be negative")
    private Integer lessonCount;

    @JsonProperty("word_count")
    @PositiveOrZero(message = "Word count must not be negative")
    private Integer wordCount;
}
