package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MistakeRequest {

    @NotNull(message = "User ID cannot be null")
    @JsonProperty("user_id")
    private Long userId;

    @NotNull(message = "Lesson ID cannot be null")
    @JsonProperty("lesson_id")
    private Long lessonId;

    @NotNull(message = "Question ID cannot be null")
    @JsonProperty("question_id")
    private Long questionId;

    @JsonProperty("is_active")
    private Boolean active;
<<<<<<< HEAD

    @JsonProperty("your_answer") // Thêm trường này
    private String yourAnswer; // Sửa kiểu dữ liệu thành String
=======
>>>>>>> origin/Hung2
}