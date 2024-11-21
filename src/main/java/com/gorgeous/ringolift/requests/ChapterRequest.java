package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChapterRequest {
    @JsonProperty("name")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    @NotBlank(message = "Name is required")
    private String name;

    @JsonProperty("cover_image")
    @Size(min = 1, max = 255, message = "Cover image url is too long")
    private String coverImage;

    @JsonProperty("description")
    private String description;
}
