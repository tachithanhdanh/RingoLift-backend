package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.Answer;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponse extends BaseResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("is_correct")
    private Boolean isCorrect;

    @JsonProperty("question_id")
    private Long questionId;

    public static AnswerResponse fromAnswer(Answer answer) {
        AnswerResponse answerResponse = AnswerResponse.builder()
                .id(answer.getId())
                .content(answer.getContent())
                .isCorrect(answer.getIsCorrect())
                .questionId(answer.getQuestion().getId())
                .build();
        // Gán các thuộc tính thời gian từ BaseResponse nếu cần
        answerResponse.setCreatedAt(answer.getCreatedAt());
        answerResponse.setUpdatedAt(answer.getUpdatedAt());
        return answerResponse;
    }
}
