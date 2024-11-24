package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.DailyProgress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyProgressResponse extends BaseResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("time_spent")
    private Integer timeSpent;

    @JsonProperty("lesson_count")
    private Integer lessonCount;

    @JsonProperty("word_count")
    private Integer wordCount;

    public static DailyProgressResponse fromDailyProgress(DailyProgress dailyProgress) {
        DailyProgressResponse dailyProgressResponse = DailyProgressResponse.builder()
                .id(dailyProgress.getId())
                .userId(dailyProgress.getUser().getId())
                .timeSpent(dailyProgress.getTimeSpent())
                .lessonCount(dailyProgress.getLessonCount())
                .wordCount(dailyProgress.getWordCount())
                .build();
        dailyProgressResponse.setCreatedAt(dailyProgress.getCreatedAt());
        dailyProgressResponse.setUpdatedAt(dailyProgress.getUpdatedAt());
        return dailyProgressResponse;
    }
}
