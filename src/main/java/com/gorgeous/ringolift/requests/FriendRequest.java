package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendRequest {
    @NotNull(message = "Receiver ID is required")
    @JsonProperty("receiver_id")
    private Long receiverId;

    @NotNull(message = "Sender ID is required")
    @JsonProperty("sender_id")
    private Long senderId;
}
