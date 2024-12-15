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
public class ChangePasswordRequest {
    @JsonProperty("username")
    @NotBlank(message = "Username is required")
    private String username;

    @JsonProperty("old_password")
    @NotBlank(message = "Old password is required")
    private String oldPassword;

    @JsonProperty("new_password")
    @NotBlank(message = "New password is required")
    private String newPassword;
}
