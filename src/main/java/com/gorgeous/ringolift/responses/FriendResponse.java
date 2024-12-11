package com.gorgeous.ringolift.responses;

import com.gorgeous.ringolift.models.Friend;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendResponse extends BaseResponse {

    private Long id;
    private Long senderId;
    private Long receiverId;
    private String statusType;
    private LocalDateTime createdAt;
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
