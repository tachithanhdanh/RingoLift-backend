package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NotificationTypeRequest {
    @NotBlank(message = "Notification type must not be blank")
    @JsonProperty("noti_type")
    private String notiType;
}
