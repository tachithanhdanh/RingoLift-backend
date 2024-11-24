package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.NotificationRequest;
import com.gorgeous.ringolift.responses.NotificationResponse;

import java.util.List;

public interface NotificationService {
    NotificationResponse createNotification(NotificationRequest request) throws DataNotFoundException;
    NotificationResponse updateNotification(Long notificationId, NotificationRequest request) throws DataNotFoundException;
    NotificationResponse getNotificationById(Long notificationId) throws DataNotFoundException;
    List<NotificationResponse> getUserNotifications(Long userId) throws DataNotFoundException; // Add exception here
    void deleteNotification(Long notificationId) throws DataNotFoundException;
    NotificationResponse markAsRead(Long notificationId) throws DataNotFoundException;
}

