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
public class WordRequest {

    @JsonProperty("word")
    @NotBlank
    private String word;

    @JsonProperty("meaning")
    @NotBlank
    private String meaning;

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("part_of_speech_id")
    private Long partOfSpeechId;

    @JsonProperty("pronunciation")
    private String pronunciation;

    @JsonProperty("audio_url")
    private String audioUrl;

    @JsonProperty("example_sentence")
    private String exampleSentence;
}
