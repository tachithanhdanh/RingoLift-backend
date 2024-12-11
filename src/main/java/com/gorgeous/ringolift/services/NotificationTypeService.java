package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.NotificationTypeRequest;
import com.gorgeous.ringolift.responses.NotificationTypeResponse;

import java.util.List;

public interface NotificationTypeService {

    NotificationTypeResponse createNotificationType(NotificationTypeRequest request) throws DuplicateDataException;

    List<NotificationTypeResponse> getAllNotificationTypes();

    NotificationTypeResponse getNotificationTypeById(Long id) throws DataNotFoundException;

    NotificationTypeResponse updateNotificationType(Long id, NotificationTypeRequest request) throws DataNotFoundException;

    void deleteNotificationType(Long id) throws DataNotFoundException;
}
