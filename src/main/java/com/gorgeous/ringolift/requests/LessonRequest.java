package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonRequest {
    @JsonProperty("title")
    @NotBlank(message = "Title is required")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("chapter_id")
    private Long chapterId;
}
