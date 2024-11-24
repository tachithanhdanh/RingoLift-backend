package com.gorgeous.ringolift.responses;

import com.gorgeous.ringolift.models.Answer;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponse extends BaseResponse {
    private Long id;
    private String content;
    private Boolean isCorrect;
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
