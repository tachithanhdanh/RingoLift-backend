package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.Chapter;
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
public class ChapterResponse extends BaseResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cover_image")
    private String coverImage;

    @JsonProperty("description")
    private String description;

    public static ChapterResponse fromChapter(Chapter chapter) {
        ChapterResponse chapterResponse = ChapterResponse.builder()
                .id(chapter.getId())
                .name(chapter.getName())
                .coverImage(chapter.getCoverImage())
                .description(chapter.getDescription())
                .build();
        chapterResponse.setCreatedAt(chapter.getCreatedAt());
        chapterResponse.setUpdatedAt(chapter.getUpdatedAt());
        return chapterResponse;
    }
}
