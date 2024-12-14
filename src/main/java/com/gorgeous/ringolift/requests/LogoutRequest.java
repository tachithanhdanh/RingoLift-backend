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
public class LogoutRequest {
    @JsonProperty("username")
    @NotBlank(message = "Username is required")
    private String username;

    @JsonProperty("token")
    @NotBlank(message = "Token is required")
    private String token;
}
