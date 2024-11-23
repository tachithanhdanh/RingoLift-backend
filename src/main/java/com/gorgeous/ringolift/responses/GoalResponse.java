package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.Goal;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoalResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("time_spent")
    private Integer timeSpent;

    @JsonProperty("lesson_count")
    private Integer lessonCount;

    @JsonProperty("word_count")
    private Integer wordCount;

    public static GoalResponse fromGoal(Goal goal) {
        return GoalResponse.builder()
                .id(goal.getId())
                .timeSpent(goal.getTimeSpent())
                .lessonCount(goal.getLessonCount())
                .wordCount(goal.getWordCount())
                .build();
    }
}
