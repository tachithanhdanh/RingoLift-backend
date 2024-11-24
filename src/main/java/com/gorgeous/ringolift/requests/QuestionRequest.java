package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.Question;
import com.gorgeous.ringolift.models.QuestionType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionRequest {
    @JsonProperty("content")
    @NotBlank(message = "Content is required")
    private String content;

    @JsonProperty("audio_url")
    @Max(value = 255, message = "Audio url is too long")
    private String audioUrl;

    @JsonProperty("hint")
    private String hint;

    @JsonProperty("type_id")
    @NotNull(message = "Type id is required")
    private Long typeId;
}
