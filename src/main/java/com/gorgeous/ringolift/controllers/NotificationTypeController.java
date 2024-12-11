package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.requests.NotificationTypeRequest;
import com.gorgeous.ringolift.responses.NotificationTypeResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.NotificationTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/notification-types")
@RequiredArgsConstructor
public class NotificationTypeController {

    private final NotificationTypeService notificationTypeService;

    // Create a new NotificationType
    // POST localhost:8088/api/v1/notification-types
    @PostMapping("")
    public ResponseEntity<ResponseObject> createNotificationType(
            @Valid @RequestBody NotificationTypeRequest notificationTypeRequest) {
        try {
            NotificationTypeResponse response = notificationTypeService.createNotificationType(notificationTypeRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(ResponseObject.builder()
                    .message("Notification Type created successfully")
                    .data(response)
                    .status(HttpStatus.CREATED)
                    .build());
        } catch (DuplicateDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseObject.builder()
                    .message("Error: " + e.getMessage())
                    .status(HttpStatus.BAD_REQUEST)
                    .build());
        }
    }

    // Get all NotificationTypes
    // GET localhost:8088/api/v1/notification-types
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllNotificationTypes() {
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get list of notification types successfully")
                        .data(notificationTypeService.getAllNotificationTypes())
                        .status(HttpStatus.OK)
                        .build());
    }

    // Get NotificationType by ID
    // GET localhost:8088/api/v1/notification-types/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getNotificationTypeById(
            @Valid @PathVariable("id") Long id) {
        try {
            NotificationTypeResponse response = notificationTypeService.getNotificationTypeById(id);
            return ResponseEntity.ok(
                    ResponseObject.builder()
                            .message("Get notification type by id successfully")
                            .data(response)
                            .status(HttpStatus.OK)
                            .build());
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseObject.builder()
                    .message("Error: " + e.getMessage())
                    .status(HttpStatus.NOT_FOUND)
                    .build());
        }
    }

    // Update NotificationType by ID
    // PUT localhost:8088/api/v1/notification-types/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateNotificationType(
            @Valid @PathVariable("id") Long id,
            @Valid @RequestBody NotificationTypeRequest notificationTypeRequest) {
        try {
            NotificationTypeResponse response = notificationTypeService.updateNotificationType(id, notificationTypeRequest);
            return ResponseEntity.ok(
                    ResponseObject.builder()
                            .message("Notification Type updated successfully")
                            .data(response)
                            .status(HttpStatus.OK)
                            .build());
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseObject.builder()
                    .message("Error: " + e.getMessage())
                    .status(HttpStatus.NOT_FOUND)
                    .build());
        }
    }

    // Delete NotificationType by ID
    // DELETE localhost:8088/api/v1/notification-types/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteNotificationType(
            @Valid @PathVariable("id") Long id) {
        try {
            notificationTypeService.deleteNotificationType(id);
            return ResponseEntity.ok(
                    ResponseObject.builder()
                            .message("Notification Type deleted successfully")
                            .status(HttpStatus.OK)
                            .build());
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseObject.builder()
                    .message("Error: " + e.getMessage())
                    .status(HttpStatus.NOT_FOUND)
                    .build());
        }
    }
}
