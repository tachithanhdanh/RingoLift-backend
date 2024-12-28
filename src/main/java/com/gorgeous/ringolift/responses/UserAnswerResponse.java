package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.UserAnswer;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAnswerResponse extends BaseResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("question_id")
    private Long questionId;

    @JsonProperty("answer_text")
    private String answerText;

    public UserAnswerResponse(UserAnswer userAnswer) {
        this.id = userAnswer.getId();
        this.userId = userAnswer.getUser().getId();
        this.questionId = userAnswer.getQuestion().getId();
        this.answerText = userAnswer.getAnswerText();
        this.setCreatedAt(userAnswer.getCreatedAt());
        this.setUpdatedAt(userAnswer.getUpdatedAt());
    }

    // Add a static method to create UserAnswerResponse from UserAnswer
    public static UserAnswerResponse fromUserAnswer(UserAnswer userAnswer) {
        return new UserAnswerResponse(userAnswer);
    }
}

