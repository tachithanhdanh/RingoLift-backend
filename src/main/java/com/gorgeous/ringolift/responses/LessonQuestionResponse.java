package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.LessonQuestion;
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
public class LessonQuestionResponse extends BaseResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("lesson_id")
    private Long lessonId;

    @JsonProperty("question_id")
    private Long questionId;

    public static LessonQuestionResponse fromLessonQuestion(LessonQuestion lessonQuestion) {
        LessonQuestionResponse lessonQuestionResponse = LessonQuestionResponse.builder()
                .id(lessonQuestion.getId())
                .lessonId(lessonQuestion.getLesson().getId())
                .questionId(lessonQuestion.getQuestion().getId())
                .build();
        lessonQuestionResponse.setCreatedAt(lessonQuestion.getCreatedAt());
        lessonQuestionResponse.setUpdatedAt(lessonQuestion.getUpdatedAt());
        return lessonQuestionResponse;
    }

    public static List<LessonQuestionResponse> fromLessonQuestionList(
            List<LessonQuestion> lessonQuestions) {
        return lessonQuestions.stream()
                .map(LessonQuestionResponse::fromLessonQuestion)
                .toList();
    }
}
