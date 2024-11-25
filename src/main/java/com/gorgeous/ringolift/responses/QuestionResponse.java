package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.Question;
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
public class QuestionResponse extends BaseResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("content")
    private String content;

    @JsonProperty("audio_url")
    private String audioUrl;

    @JsonProperty("hint")
    private String hint;

    @JsonProperty("type")
    private String type;

    public static QuestionResponse fromQuestion(Question question) {
        QuestionResponse questionResponse = QuestionResponse.builder()
                .id(question.getId())
                .content(question.getContent())
                .audioUrl(question.getAudioUrl())
                .hint(question.getHint())
                .type(question.getType().toString())
                .build();
        questionResponse.setCreatedAt(question.getCreatedAt());
        questionResponse.setUpdatedAt(question.getUpdatedAt());
        return questionResponse;
    }

    public static List<QuestionResponse> fromQuestions(List<Question> questions) {
        return questions.stream()
                .map(QuestionResponse::fromQuestion)
                .toList();
    }
}
