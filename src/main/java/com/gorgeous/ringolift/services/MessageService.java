package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.MessageRequest;
import com.gorgeous.ringolift.responses.MessageResponse;
import java.util.List;

public interface MessageService {
    // Phương thức tạo tin nhắn mới
    MessageResponse createMessage(MessageRequest messageRequest) throws DataNotFoundException;

    // Phương thức cập nhật tin nhắn
    MessageResponse updateMessage(Long messageId, MessageRequest messageRequest)
            throws DataNotFoundException;

    // Phương thức lấy tin nhắn theo ID
    MessageResponse getMessageById(Long messageId) throws DataNotFoundException;

    // Phương thức lấy danh sách tin nhắn giữa 2 người dùng
    List<MessageResponse> getMessagesBetweenUsers(Long senderId, Long receiverId);

    // Phương thức lấy danh sách tin nhắn của một người dùng
    List<MessageResponse> getMessagesForUser(Long userId);

    // Phương thức xóa tin nhắn theo ID
    void deleteMessage(Long messageId) throws DataNotFoundException;

    // Phương thức đánh dấu tin nhắn là đã đọc
    void markMessageAsRead(Long messageId) throws DataNotFoundException;
}
