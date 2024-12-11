package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse extends BaseResponse {
    @JsonProperty("id")

    private Long id; // Sửa Integer thành Long

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("notification_type")
    private String notificationType;

    @JsonProperty("content")
    private String content;

    @JsonProperty("is_read")
    private Boolean isRead;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;

    // Phương thức chuyển đổi từ model Notification sang NotificationResponse
    public static NotificationResponse fromNotification(com.gorgeous.ringolift.models.Notification notification) {

        NotificationResponse notificationResponse = NotificationResponse.builder()
                .id(notification.getId())
                .userName(notification.getUser() != null ? notification.getUser().getUsername() : null)
                .notificationType(notification.getNotificationType() != null ? notification.getNotificationType().getNotiType() : null)
                .content(notification.getContent())
                .isRead(notification.getIsRead())
                .isDeleted(notification.getIsDeleted())
                .build();

        notificationResponse.setCreatedAt(notification.getCreatedAt());
        notificationResponse.setUpdatedAt(notification.getUpdatedAt());

        return notificationResponse;
    }


}
