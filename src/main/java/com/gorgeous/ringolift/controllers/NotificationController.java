package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.NotificationRequest;
import com.gorgeous.ringolift.responses.NotificationResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    /**
     * Create a new notification.
     * POST /api/v1/notifications
     */
    @PostMapping("")
    public ResponseEntity<ResponseObject> createNotification(@RequestBody NotificationRequest request) {
        try {
            NotificationResponse response = notificationService.createNotification(request);
            return ResponseEntity.ok(new ResponseObject("Notification created successfully", HttpStatus.OK, response));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Error: " + e.getMessage(), HttpStatus.NOT_FOUND, null));
        }
    }

    /**
     * Retrieve the list of notifications for a specific user.
     * GET /api/v1/notifications/{userId}
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ResponseObject> getUserNotifications(@PathVariable Long userId) {
        try {
            List<NotificationResponse> responses = notificationService.getUserNotifications(userId);
            return ResponseEntity.ok(new ResponseObject("Fetched notifications successfully", HttpStatus.OK, responses));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Error: " + e.getMessage(), HttpStatus.NOT_FOUND, null));
        }
    }

    /**
     * Mark a notification as read.
     * PATCH /api/v1/notifications/{notificationId}/read
     */
    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<ResponseObject> markAsRead(@PathVariable Long notificationId) {
        try {
            NotificationResponse response = notificationService.markAsRead(notificationId);
            return ResponseEntity.ok(new ResponseObject("Notification marked as read", HttpStatus.OK, response));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Error: " + e.getMessage(), HttpStatus.NOT_FOUND, null));
        }
    }

    /**
     * Delete a notification.
     * DELETE /api/v1/notifications/{notificationId}
     */
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<ResponseObject> deleteNotification(@PathVariable Long notificationId) {
        try {
            notificationService.deleteNotification(notificationId);  // Delete the notification
            return ResponseEntity.ok(new ResponseObject("Notification deleted successfully", HttpStatus.OK, null));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Error: " + e.getMessage(), HttpStatus.NOT_FOUND, null));
        }
    }
}
