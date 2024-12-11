package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.QuestionType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionTypeResponse extends BaseResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("type")
    private String type;

    public static QuestionTypeResponse fromQuestionType(QuestionType questionType) {
        QuestionTypeResponse questionTypeResponse = QuestionTypeResponse.builder()
                .id(questionType.getId())
                .type(questionType.getType().toString())
                .build();
        questionTypeResponse.setCreatedAt(questionType.getCreatedAt());
        questionTypeResponse.setUpdatedAt(questionType.getUpdatedAt());
        return questionTypeResponse;
    }

    public static List<QuestionTypeResponse> fromQuestionTypes(List<QuestionType> questionTypes) {
        return questionTypes.stream()
                .map(QuestionTypeResponse::fromQuestionType)
                .toList();
    }
}
