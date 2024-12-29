package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @JsonProperty("username")
    @NotBlank(message = "Username is required")
    @Size(max = 255, message = "Username must be less than 255 characters")
    private String username;

    @JsonProperty("email")
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @JsonProperty("first_name")
    @Size(max = 255, message = "First name must be less than 255 characters")
    private String firstName;

    @JsonProperty("last_name")
    @Size(max = 255, message = "Last name must be less than 255 characters")
    private String lastName;

    @JsonProperty("date_of_birth")
    private LocalDateTime dateOfBirth;

    @JsonProperty("gender_id")
    private Long genderId;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("goal_id")
    private Long goalId;

//    @JsonProperty("password")
//    @NotBlank(message = "Password is required")
//    private String password;

    @JsonProperty("is_public")
    private Boolean isPublic;

    @JsonProperty("google_id")
    private String googleId;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("role")
    private String role;
}
