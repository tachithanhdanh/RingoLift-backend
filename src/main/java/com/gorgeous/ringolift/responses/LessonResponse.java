package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.Lesson;
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
public class LessonResponse extends BaseResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("chapter_id")
    private Long chapterId;

    @JsonProperty("chapter_name")
    private String chapterName;

    public static LessonResponse fromLesson(Lesson lesson) {
        LessonResponse lessonResponse = LessonResponse.builder()
                .id(lesson.getId())
                .title(lesson.getTitle())
                .description(lesson.getDescription())
                .chapterId(lesson.getChapter().getId())
                .chapterName(lesson.getChapter().getName())
                .build();
        lessonResponse.setCreatedAt(lesson.getCreatedAt());
        lessonResponse.setUpdatedAt(lesson.getUpdatedAt());
        return lessonResponse;
    }
}
