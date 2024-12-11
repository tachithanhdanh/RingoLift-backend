package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Message;
import com.gorgeous.ringolift.repositories.MessageRepository;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.requests.MessageRequest;
import com.gorgeous.ringolift.responses.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        logger.info("MessageServiceImpl initialized");
    }

    @Override
    public MessageResponse createMessage(MessageRequest messageRequest) throws DataNotFoundException {
        logger.info("Creating new message - SenderId: {}, ReceiverId: {}",
                messageRequest.getSenderId(), messageRequest.getReceiverId());

        // Kiểm tra sự tồn tại của sender
        if (!userRepository.existsById(messageRequest.getSenderId())) {
            logger.error("Sender not found with ID: {}", messageRequest.getSenderId());
            throw new DataNotFoundException("Sender not found with ID " + messageRequest.getSenderId());
        }

        // Kiểm tra sự tồn tại của receiver
        if (!userRepository.existsById(messageRequest.getReceiverId())) {
            logger.error("Receiver not found with ID: {}", messageRequest.getReceiverId());
            throw new DataNotFoundException("Receiver not found with ID " + messageRequest.getReceiverId());
        }

        try {
            // Tạo đối tượng Message
            Message message = Message.builder()
                    .senderId(messageRequest.getSenderId())
                    .receiverId(messageRequest.getReceiverId())
                    .messageText(messageRequest.getMessageText())
                    .isRead(false)
                    .build();

            logger.debug("Saving message to database");

            // Lưu tin nhắn vào cơ sở dữ liệu
            Message savedMessage = messageRepository.save(message);
            logger.info("Message saved successfully with ID: {}", savedMessage.getId());

            // Chuyển đổi sang Response và trả về
            return convertToResponse(savedMessage);
        } catch (Exception e) {
            logger.error("Error while saving message", e);
            throw e;
        }
    }

    @Override
    public MessageResponse updateMessage(Long messageId, MessageRequest messageRequest) throws DataNotFoundException {
        logger.info("Updating message - MessageId: {}", messageId);

        // Tìm tin nhắn theo ID
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> {
                    logger.error("Message not found with ID: {}", messageId);
                    return new DataNotFoundException("Message not found with ID " + messageId);
                });

        try {
            // Kiểm tra sự tồn tại của sender và receiver
            if (!userRepository.existsById(messageRequest.getSenderId())) {
                logger.error("Sender not found with ID: {}", messageRequest.getSenderId());
                throw new DataNotFoundException("Sender not found with ID " + messageRequest.getSenderId());
            }
            if (!userRepository.existsById(messageRequest.getReceiverId())) {
                logger.error("Receiver not found with ID: {}", messageRequest.getReceiverId());
                throw new DataNotFoundException("Receiver not found with ID " + messageRequest.getReceiverId());
            }

            // Cập nhật tin nhắn
            message.setSenderId(messageRequest.getSenderId());
            message.setReceiverId(messageRequest.getReceiverId());
            message.setMessageText(messageRequest.getMessageText());
            message.setIsRead(messageRequest.getIsRead());

            logger.debug("Saving updated message to database");

            // Lưu tin nhắn đã cập nhật
            Message updatedMessage = messageRepository.save(message);
            logger.info("Message updated successfully - MessageId: {}", messageId);

            return convertToResponse(updatedMessage);
        } catch (DataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error while updating message", e);
            throw e;
        }
    }

    @Override
    public MessageResponse getMessageById(Long messageId) throws DataNotFoundException {
        logger.info("Retrieving message - MessageId: {}", messageId);

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> {
                    logger.error("Message not found with ID: {}", messageId);
                    return new DataNotFoundException("Message not found with ID " + messageId);
                });

        logger.info("Message retrieved successfully - MessageId: {}", messageId);
        return convertToResponse(message);
    }

    @Override
    public List<MessageResponse> getMessagesBetweenUsers(Long senderId, Long receiverId) {
        logger.info("Retrieving messages between users - SenderId: {}, ReceiverId: {}", senderId, receiverId);

        List<Message> messages = messageRepository.findBySenderIdAndReceiverId(senderId, receiverId);
        logger.info("Retrieved {} messages between users", messages.size());

        return messages.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageResponse> getMessagesForUser(Long userId) {
        logger.info("Retrieving messages for user - UserId: {}", userId);

        List<Message> messages = messageRepository.findByReceiverId(userId);
        logger.info("Retrieved {} messages for user", messages.size());

        return messages.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMessage(Long messageId) throws DataNotFoundException {
        logger.info("Deleting message - MessageId: {}", messageId);

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> {
                    logger.error("Message not found with ID: {}", messageId);
                    return new DataNotFoundException("Message not found with ID " + messageId);
                });

        try {
            messageRepository.delete(message);
            logger.info("Message deleted successfully - MessageId: {}", messageId);
        } catch (Exception e) {
            logger.error("Error while deleting message", e);
            throw e;
        }
    }

    @Override
    public void markMessageAsRead(Long messageId) throws DataNotFoundException {
        logger.info("Marking message as read - MessageId: {}", messageId);

        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> {
                    logger.error("Message not found with ID: {}", messageId);
                    return new DataNotFoundException("Message not found with ID " + messageId);
                });

        try {
            message.setIsRead(true);
            messageRepository.save(message);
            logger.info("Message marked as read successfully - MessageId: {}", messageId);
        } catch (Exception e) {
            logger.error("Error while marking message as read", e);
            throw e;
        }
    }

    private MessageResponse convertToResponse(Message message) {
        return MessageResponse.fromMessage(message);
    }

}