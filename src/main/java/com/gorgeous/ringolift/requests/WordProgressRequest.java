package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordProgressRequest {

    @JsonProperty("user_id")
    @PositiveOrZero
    private Long userId;

    @JsonProperty("word_id")
    @PositiveOrZero
    private Long wordId;

    @JsonProperty("status_id")
    @PositiveOrZero
    private Long statusId;

    @JsonProperty("note")
    private String note;
}
