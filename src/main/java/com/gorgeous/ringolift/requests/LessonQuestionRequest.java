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
public class LessonQuestionRequest {
    @JsonProperty("lesson_id")
    private Long lessonId;

    @JsonProperty("question_id")
    private Long questionId;
}
