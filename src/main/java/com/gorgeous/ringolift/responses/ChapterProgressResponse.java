package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.ChapterProgress;
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
public class ChapterProgressResponse extends BaseResponse{
    @JsonProperty("id")
    private Long id;

    @JsonProperty("unlocked")
    private Boolean unlocked;

    @JsonProperty("chapter_id")
    private Long chapterId;

    @JsonProperty("user_id")
    private Long userId;

    public static ChapterProgressResponse fromChapterProgress(ChapterProgress chapterProgress) {
        ChapterProgressResponse chapterProgressResponse = ChapterProgressResponse.builder()
                .id(chapterProgress.getId())
                .unlocked(chapterProgress.getUnlocked())
                .chapterId(chapterProgress.getChapter().getId())
                .userId(chapterProgress.getUser().getId())
                .build();
        chapterProgressResponse.setCreatedAt(chapterProgress.getCreatedAt());
        chapterProgressResponse.setUpdatedAt(chapterProgress.getUpdatedAt());
        return chapterProgressResponse;
    }
}
