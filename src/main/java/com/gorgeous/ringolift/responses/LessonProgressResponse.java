package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.LessonProgress;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonProgressResponse extends BaseResponse{
    @JsonProperty("id")
    private Long id;

    @JsonProperty("correct_count")
    private Integer correctCount;

    @JsonProperty("incorrect_count")
    private Integer incorrectCount;

    @JsonProperty("time_spent")
    private Long timeSpent;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("lesson_id")
    private Long lessonId;

    public static LessonProgressResponse fromLessonProgress(LessonProgress lessonProgress) {
        LessonProgressResponse lessonProgressResponse = LessonProgressResponse.builder()
                .id(lessonProgress.getId())
                .correctCount(lessonProgress.getCorrectCount())
                .incorrectCount(lessonProgress.getIncorrectCount())
                .timeSpent(lessonProgress.getTimeSpent())
                .userId(lessonProgress.getUser().getId())
                .lessonId(lessonProgress.getLesson().getId())
                .build();
        lessonProgressResponse.setCreatedAt(lessonProgress.getCreatedAt());
        lessonProgressResponse.setUpdatedAt(lessonProgress.getUpdatedAt());
        return lessonProgressResponse;
    }

    public static List<LessonProgressResponse> fromLessonProgressList(List<LessonProgress> lessonProgressList) {
        return lessonProgressList.stream().map(LessonProgressResponse::fromLessonProgress).toList();
    }
}
