package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse extends BaseResponse {
    @JsonProperty("id")
    private Long id; // Sửa Integer thành Long

    @JsonProperty("sender_id")
    private Long senderId;

    @JsonProperty("receiver_id")
    private Long receiverId;

    @JsonProperty("message_text")
    private String messageText;

    @JsonProperty("is_read")
    private Boolean isRead;



    // Phương thức chuyển đổi từ model Message sang MessageResponse
    public static MessageResponse fromMessage(com.gorgeous.ringolift.models.Message message) {
        MessageResponse messageResponse = MessageResponse.builder()
                .id(message.getId())
                .senderId(message.getSenderId())
                .receiverId(message.getReceiverId())
                .messageText(message.getMessageText())
                .isRead(message.getIsRead())
                .build();
        messageResponse.setCreatedAt(message.getCreatedAt());
        messageResponse.setUpdatedAt(message.getUpdatedAt());
        return messageResponse;
    }
}
