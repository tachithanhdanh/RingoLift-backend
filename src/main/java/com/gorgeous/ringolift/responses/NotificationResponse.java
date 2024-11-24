package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationResponse extends BaseResponse {

    private Long id; // Sửa Integer thành Long

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("notification_type")
    private String notificationType;

    private String content;

    @JsonProperty("is_read")
    private Boolean isRead;

    @JsonProperty("is_deleted")
    private Boolean isDeleted;

    // Phương thức chuyển đổi từ model Notification sang NotificationResponse
    public static NotificationResponse fromNotification(com.gorgeous.ringolift.models.Notification notification) {
        // Thêm logging để kiểm tra user và notificationType có null không
        System.out.println("User: " + notification.getUser());
        System.out.println("Notification Type: " + notification.getNotificationType());

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
