package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Notification;
import com.gorgeous.ringolift.models.NotificationType;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.repositories.NotificationTypeRepository;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.repositories.NotificationRepository;
import com.gorgeous.ringolift.requests.NotificationRequest;
import com.gorgeous.ringolift.responses.NotificationResponse;
import com.gorgeous.ringolift.services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {
    private final NotificationRepository notificationRepository;

    private final UserRepository userRepository;

    private final NotificationTypeRepository notificationTypeRepository;

    @Override
    public NotificationResponse createNotification(NotificationRequest request) throws DataNotFoundException {
        // Load User từ database thay vì chỉ set id
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + request.getUserId()));

        // Load NotificationType từ database thay vì chỉ set id
        NotificationType notificationType = notificationTypeRepository.findById(request.getTypeId())
                .orElseThrow(() -> new DataNotFoundException("NotificationType not found with id: " + request.getTypeId()));

        Notification notification = new Notification();
        notification.setUser(user);
        notification.setNotificationType(notificationType);
        notification.setContent(request.getContent());
        notification.setIsRead(false);
        notification.setIsDeleted(false);

        Notification savedNotification = notificationRepository.save(notification);

        return mapToResponse(savedNotification);
    }

    @Override
    public NotificationResponse updateNotification(Long notificationId, NotificationRequest request) throws DataNotFoundException {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new DataNotFoundException("Notification not found"));

        User user = new User();
        user.setId(request.getUserId());

        NotificationType notificationType = new NotificationType();
        notificationType.setId(request.getTypeId());

        notification.setUser(user);
        notification.setNotificationType(notificationType);
        notification.setContent(request.getContent());

        Notification updatedNotification = notificationRepository.save(notification);
        return mapToResponse(updatedNotification);
    }

    @Override
    public NotificationResponse getNotificationById(Long notificationId) throws DataNotFoundException {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new DataNotFoundException("Notification not found"));

        return mapToResponse(notification);
    }

    @Override
    public List<NotificationResponse> getUserNotifications(Long userId) throws DataNotFoundException {
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        if (notifications.isEmpty()) {
            throw new DataNotFoundException("No notifications found for user with id " + userId);
        }

        return notifications.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteNotification(Long notificationId) throws DataNotFoundException {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new DataNotFoundException("Notification not found"));

        // Xóa thông báo khỏi cơ sở dữ liệu
        notificationRepository.delete(notification);
    }

    @Override
    public NotificationResponse markAsRead(Long notificationId) throws DataNotFoundException {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new DataNotFoundException("Notification not found"));

        notification.setIsRead(true);
        Notification updatedNotification = notificationRepository.save(notification);

        return mapToResponse(updatedNotification);
    }

    private final NotificationResponse mapToResponse(Notification notification) {
        return NotificationResponse.fromNotification(notification);
    }
}

