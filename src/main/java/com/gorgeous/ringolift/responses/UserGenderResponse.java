package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.UserGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGenderResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("gender_type")
    private String genderType;

    public static UserGenderResponse fromUserGender(UserGender userGender) {
        return UserGenderResponse.builder()
                .id(userGender.getId())
                .genderType(userGender.getGenderType())
                .build();
    }
}
