package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerRequest {

    @NotNull(message = "User ID cannot be null")
    @JsonProperty("user_id")
    private Long userId;

    @NotNull(message = "Question ID cannot be null")
    @JsonProperty("question_id")
    private Long questionId;

    @NotBlank(message = "Answer text cannot be blank")
    @JsonProperty("answer_text")
    private String answerText;
}

