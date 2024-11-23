package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.User;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class UserResponse extends BaseResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("date_of_birth")
    private LocalDateTime dateOfBirth;

    @JsonProperty("gender")
    private String genderType;

    @JsonProperty("picture")
    private String picture;

    @JsonProperty("goal")
    private GoalResponse goal;

    @JsonProperty("is_public")
    private Boolean isPublic;

    @JsonProperty("google_id")
    private String googleId;

    @JsonProperty("access_token")
    private String accessToken;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .genderType(user.getGender() != null ? user.getGender().getGenderType() : null)
                .picture(user.getPicture())
                .goal(user.getGoal() != null ? GoalResponse.fromGoal(user.getGoal()) : null)
                .isPublic(user.getIsPublic())
                .googleId(user.getGoogleId())
                .accessToken(user.getAccessToken())
                .build();
    }
}
