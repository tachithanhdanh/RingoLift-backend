package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.Word;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordResponse extends BaseResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("word")
    private String word;

    @JsonProperty("meaning")
    private String meaning;

    @JsonProperty("topic_id")
    private Long topicId;

    @JsonProperty("part_of_speech_id")
    private Long partOfSpeechId;

    @JsonProperty("pronunciation")
    private String pronunciation;

    @JsonProperty("audio_url")
    private String audioUrl;

    @JsonProperty("example_sentence")
    private String exampleSentence;

    public static WordResponse fromWord(Word word) {
        WordResponse wordResponse = WordResponse.builder()
                .id(word.getId())
                .word(word.getWord())
                .meaning(word.getMeaning())
                .pronunciation(word.getPronunciation())
                .audioUrl(word.getAudioUrl())
                .exampleSentence(word.getExampleSentence())
                .partOfSpeechId(word.getPartOfSpeech() == null ? null : word.getPartOfSpeech().getId())
                .topicId(word.getTopic() == null ? null : word.getTopic().getId())
                .build();
        wordResponse.setCreatedAt(word.getCreatedAt());
        wordResponse.setUpdatedAt(word.getUpdatedAt());
        return wordResponse;
    }
}
