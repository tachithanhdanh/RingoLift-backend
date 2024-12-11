package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.NotificationType;
import com.gorgeous.ringolift.repositories.NotificationTypeRepository;
import com.gorgeous.ringolift.requests.NotificationTypeRequest;
import com.gorgeous.ringolift.responses.NotificationTypeResponse;
import com.gorgeous.ringolift.services.NotificationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationTypeServiceImpl implements NotificationTypeService {

    @Autowired
    private NotificationTypeRepository repository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public NotificationTypeResponse createNotificationType(NotificationTypeRequest request) throws DuplicateDataException {
        // Kiểm tra xem loại thông báo đã tồn tại chưa
        if (repository.existsByNotiType(request.getNotiType())) {
            throw new DuplicateDataException("Notification type already exists.");
        }

        NotificationType notificationType = new NotificationType();
        notificationType.setNotiType(request.getNotiType());

        NotificationType savedNotificationType = repository.save(notificationType);

        return mapToResponse(savedNotificationType);
    }

    @Override
    public List<NotificationTypeResponse> getAllNotificationTypes() {
        return repository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationTypeResponse getNotificationTypeById(Long id) throws DataNotFoundException {
        NotificationType notificationType = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Notification type not found with id: " + id));

        return mapToResponse(notificationType);
    }

    @Override
    public NotificationTypeResponse updateNotificationType(Long id, NotificationTypeRequest request) throws DataNotFoundException {
        NotificationType notificationType = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Notification type not found with id: " + id));

        notificationType.setNotiType(request.getNotiType());
        NotificationType updatedNotificationType = repository.save(notificationType);

        return mapToResponse(updatedNotificationType);
    }

    @Override
    public void deleteNotificationType(Long id) throws DataNotFoundException {
        NotificationType notificationType = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Notification type not found with id: " + id));

        repository.delete(notificationType);
    }

    private NotificationTypeResponse mapToResponse(NotificationType notificationType) {
        NotificationTypeResponse response = new NotificationTypeResponse();
        response.setId(notificationType.getId());
        response.setNotiType(notificationType.getNotiType());
        response.setCreatedAt(notificationType.getCreatedAt().format(formatter));
        response.setUpdatedAt(notificationType.getUpdatedAt().format(formatter));
        return response;
    }
}
