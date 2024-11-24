package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerRequest {
    @NotBlank(message = "Content cannot be blank")
    private String content;

    @NotNull(message = "isCorrect cannot be null")
    @JsonProperty("is_correct")
    private Boolean isCorrect;

    @NotNull(message = "Question ID cannot be null")
    @JsonProperty("question_id")
    private Long questionId;
}
