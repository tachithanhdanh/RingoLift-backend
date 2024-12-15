package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.Friend;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendResponse extends BaseResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("sender_id")
    private Long senderId;

    @JsonProperty("receiver_id")
    private Long receiverId;

    @JsonProperty("status_type")
    private String statusType;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    public static FriendResponse fromFriend(Friend friend) {
        return FriendResponse.builder()
                .id(friend.getId())
                .senderId(friend.getSender().getId())
                .receiverId(friend.getReceiver().getId())
                .statusType(friend.getStatus().getStatusType())
                .createdAt(friend.getCreatedAt())
                .updatedAt(friend.getUpdatedAt())
                .build();
    }
}
