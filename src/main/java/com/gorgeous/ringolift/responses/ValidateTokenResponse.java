package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidateTokenResponse {
    @JsonProperty("valid")
    private boolean valid;

    @JsonProperty("message")
    private String message;

    @JsonProperty("username")
    private String username;

    @JsonProperty("expiration_date")
    private Date expirationDate;
}
