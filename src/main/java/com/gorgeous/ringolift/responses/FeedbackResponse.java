package com.gorgeous.ringolift.responses;

import com.gorgeous.ringolift.models.Feedback;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeedbackResponse extends BaseResponse {
    private Long id;
    private Long userId;
    private Long lessonId;
    private String content;

    // Method to convert a FeedBack entity to FeedBackResponse
    public static FeedbackResponse fromFeedBack(Feedback feedback) {
        FeedbackResponse feedbackResponse = FeedbackResponse.builder()
                .id(feedback.getId())
                .userId(feedback.getUser().getId())
                .lessonId(feedback.getLesson().getId())
                .content(feedback.getComment())
                .build();

        // Set BaseResponse timestamps
        feedbackResponse.setCreatedAt(feedback.getCreatedAt());
        feedbackResponse.setUpdatedAt(feedback.getUpdatedAt());
        return feedbackResponse;
    }
}
