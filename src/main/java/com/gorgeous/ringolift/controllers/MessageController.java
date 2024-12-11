package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.MessageRequest;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.MessageService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/messages")
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
        logger.info("MessageController initialized");
    }

    /**
     * Create a new message.
     * POST /api/v1/messages
     */
    @PostMapping("")
    public ResponseEntity<ResponseObject> createMessage(
            @RequestHeader(value = "User-Id", required = true) Long senderId,
            @Valid @RequestBody MessageRequest request) {
        logger.info("Received create message request - SenderId: {}, Request: {}", senderId, request);

        try {
            // Validate request
            if (request.getReceiverId() == null) {
                logger.error("ReceiverId is null in request");
                return ResponseEntity.badRequest()
                        .body(new ResponseObject("ReceiverId cannot be null", HttpStatus.BAD_REQUEST, null));
            }

            if (request.getMessageText() == null || request.getMessageText().trim().isEmpty()) {
                logger.error("MessageText is null or empty in request");
                return ResponseEntity.badRequest()
                        .body(new ResponseObject("MessageText cannot be null or empty", HttpStatus.BAD_REQUEST, null));
            }

            // Set the senderId in the request object
            request.setSenderId(senderId);
            logger.debug("Processing message creation with senderId: {}", senderId);

            // Attempt to create message
            var response = messageService.createMessage(request);
            logger.info("Message created successfully with ID: {}", response.getId());

            return ResponseEntity.ok(new ResponseObject(
                    "Message sent successfully",
                    HttpStatus.CREATED,
                    response
            ));
        } catch (DataNotFoundException e) {
            logger.error("Data not found error while creating message: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject(e.getMessage(), HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            logger.error("Unexpected error while creating message", e);
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
        logger.info("Retrieving conversation - SenderId: {}, ReceiverId: {}", senderId, receiverId);

        try {
            var messages = messageService.getMessagesBetweenUsers(senderId, receiverId);
            logger.info("Retrieved {} messages for conversation", messages.size());

            return ResponseEntity.ok(new ResponseObject(
                    "Conversation retrieved successfully",
                    HttpStatus.OK,
                    messages
            ));
        } catch (Exception e) {
            logger.error("Error retrieving conversation", e);
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
        logger.info("Retrieving inbox for userId: {}", userId);

        try {
            var messages = messageService.getMessagesForUser(userId);
            logger.info("Retrieved {} messages for user inbox", messages.size());

            return ResponseEntity.ok(new ResponseObject(
                    "Inbox retrieved successfully",
                    HttpStatus.OK,
                    messages
            ));
        } catch (Exception e) {
            logger.error("Error retrieving inbox", e);
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
        logger.info("Marking message as read - MessageId: {}", messageId);

        try {
            messageService.markMessageAsRead(messageId);
            logger.info("Message marked as read successfully - MessageId: {}", messageId);

            return ResponseEntity.ok(new ResponseObject(
                    "Message marked as read",
                    HttpStatus.OK,
                    null
            ));
        } catch (DataNotFoundException e) {
            logger.error("Message not found for marking as read - MessageId: {}", messageId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Message not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            logger.error("Error marking message as read - MessageId: {}", messageId, e);
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
        logger.info("Deleting message - MessageId: {}", messageId);

        try {
            // Call service to delete message
            messageService.deleteMessage(messageId);
            logger.info("Message deleted successfully - MessageId: {}", messageId);

            return ResponseEntity.ok(new ResponseObject(
                    "Message deleted successfully",
                    HttpStatus.OK,
                    null
            ));
        } catch (DataNotFoundException e) {
            logger.error("Message not found for deletion - MessageId: {}", messageId);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ResponseObject("Message not found", HttpStatus.NOT_FOUND, null));
        } catch (Exception e) {
            logger.error("Error deleting message - MessageId: {}", messageId, e);
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
        logger.error("Missing required header: {}", ex.getHeaderName());
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
        logger.error("Data not found: {}", ex.getMessage());
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
        logger.error("Internal server error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ResponseObject(
                        "Internal server error: " + ex.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        null
                ));
    }
}
