package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.Mistake;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MistakeResponse extends BaseResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("lesson_id")
    private Long lessonId;

    @JsonProperty("question_id")
    private Long questionId;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("your_answer") // Thêm trường này
    private String yourAnswer; // Sửa kiểu dữ liệu thành String

    public MistakeResponse(Mistake mistake) {
        this.id = mistake.getId();
        this.userId = mistake.getUser().getId();
        this.lessonId = mistake.getLesson().getId();
        this.questionId = mistake.getQuestion().getId();
        this.active = mistake.getActive();
        this.yourAnswer = mistake.getYourAnswer();
        this.setCreatedAt(mistake.getCreatedAt());
        this.setUpdatedAt(mistake.getUpdatedAt());
    }

    // Add a static method to create MistakeResponse from Mistake
    public static MistakeResponse fromMistake(Mistake mistake) {
        return new MistakeResponse(mistake);
    }
}