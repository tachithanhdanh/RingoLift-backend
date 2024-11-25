package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.WordProgress;
import com.gorgeous.ringolift.requests.WordProgressRequest;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordProgressResponse extends BaseResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("word_id")
    private Long wordId;

    @JsonProperty("status_id")
    private Long statusId;

    @JsonProperty("note")
    private String note;

    public static WordProgressResponse fromWordProgress(WordProgress wordProgress) {
        WordProgressResponse wordProgressResponse = WordProgressResponse.builder()
                .id(wordProgress.getId())
                .userId(wordProgress.getUser().getId())
                .wordId(wordProgress.getWord().getId())
                .statusId(wordProgress.getStatus().getId())
                .note(wordProgress.getNote())
                .build();
        wordProgressResponse.setCreatedAt(wordProgress.getCreatedAt());
        wordProgressResponse.setUpdatedAt(wordProgress.getUpdatedAt());
        return wordProgressResponse;
    }
}
