package com.gorgeous.ringolift.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendRequest {
    @NotNull(message = "Receiver ID is required")
    private Long receiverId;

    @NotNull(message = "Sender ID is required")
    private Long senderId;
}
