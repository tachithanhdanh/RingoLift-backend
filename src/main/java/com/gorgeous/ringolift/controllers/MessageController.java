package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.MessageRequest;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/messages")
public class MessageController {
    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Create a new message.
     * POST /api/v1/messages
     */
    @PostMapping("")
    public ResponseEntity<ResponseObject> createMessage(
            @Valid @RequestBody MessageRequest request) {
        try {
            // Validate request
            if (request.getSenderId() == null) {
                return ResponseEntity.badRequest()
                        .body(new ResponseObject("SenderId cannot be null or empty", HttpStatus.BAD_REQUEST, null));
            }

            if (request.getReceiverId() == null) {
                return ResponseEntity.badRequest()
                        .body(new ResponseObject("ReceiverId cannot be null", HttpStatus.BAD_REQUEST, null));
            }

            if (request.getMessageText() == null || request.getMessageText().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ResponseObject("MessageText cannot be null or empty", HttpStatus.BAD_REQUEST, null));
            }

            if (request.getIsRead() == null) {
                return ResponseEntity.badRequest()
                        .body(new ResponseObject("IsRead cannot be null or empty", HttpStatus.BAD_REQUEST, null));
            }

            // Attempt to create message
            var response = messageService.createMessage(request);

            return ResponseEntity.ok(new ResponseObject(
                    "Message sent successfully",
                    HttpStatus.CREATED,
                    response
            ));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(e.getMessage(), HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error sending message: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Retrieve conversation between two users.
     * GET /api/v1/messages/conversation/{receiverId}
     */
    @GetMapping("/conversation/{receiverId}")
    public ResponseEntity<ResponseObject> getConversation(
            @RequestHeader(value = "User-Id", required = true) Long senderId,
            @PathVariable Long receiverId) {

        try {
            var messages = messageService.getMessagesBetweenUsers(senderId, receiverId);
            return ResponseEntity.ok(new ResponseObject(
                    "Conversation retrieved successfully",
                    HttpStatus.OK,
                    messages
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error retrieving conversation: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Retrieve inbox for a user.
     * GET /api/v1/messages/inbox
     */
    @GetMapping("/inbox")
    public ResponseEntity<ResponseObject> getInbox(
            @RequestHeader(value = "User-Id", required = true) Long userId) {
        try {
            var messages = messageService.getMessagesForUser(userId);
            return ResponseEntity.ok(new ResponseObject(
                    "Inbox retrieved successfully",
                    HttpStatus.OK,
                    messages
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error retrieving inbox: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Mark a message as read.
     * PATCH /api/v1/messages/{messageId}/read
     */
    @PatchMapping("/{messageId}/read")
    public ResponseEntity<ResponseObject> markAsRead(@PathVariable Long messageId) {
        try {
            messageService.markMessageAsRead(messageId);
            return ResponseEntity.ok(new ResponseObject(
                    "Message marked as read",
                    HttpStatus.OK,
                    null
            ));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Message not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error marking message as read: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Delete a message.
     * DELETE /api/v1/messages/{messageId}
     */
    @DeleteMapping("/{messageId}")
    public ResponseEntity<ResponseObject> deleteMessage(@PathVariable Long messageId) {
        try {
            // Call service to delete message
            messageService.deleteMessage(messageId);

            return ResponseEntity.ok(new ResponseObject(
                    "Message deleted successfully",
                    HttpStatus.OK,
                    null
            ));
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Message not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseObject("Error deleting message: " + e.getMessage(),
                            HttpStatus.INTERNAL_SERVER_ERROR, null));
        }
    }

    /**
     * Handle MissingRequestHeaderException.
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ResponseObject> handleMissingHeader(MissingRequestHeaderException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseObject(
                        "Missing required header: " + ex.getHeaderName(),
                        HttpStatus.BAD_REQUEST,
                        null
                ));
    }

    /**
     * Handle DataNotFoundException.
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ResponseObject> handleDataNotFound(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ResponseObject(
                        ex.getMessage(),
                        HttpStatus.NOT_FOUND,
                        null
                ));
    }

    /**
     * Handle generic errors.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> handleGenericError(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseObject(
                        "Internal server error: " + ex.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        null
                ));
    }
}
