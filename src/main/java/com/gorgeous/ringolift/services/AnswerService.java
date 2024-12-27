package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.AnswerRequest;
import com.gorgeous.ringolift.responses.AnswerResponse;

import java.util.List;

public interface AnswerService {
    // Phương thức tạo câu trả lời mới
    AnswerResponse createAnswer(AnswerRequest request) throws DataNotFoundException;

    // Phương thức cập nhật câu trả lời
    AnswerResponse updateAnswer(Long answerId, Long questionId, AnswerRequest request) throws DataNotFoundException;

    // Phương thức xóa câu trả lời theo ID
    void deleteAnswer(Long questionId, Long answerId) throws DataNotFoundException;

    // Phương thức lấy câu trả lời theo ID của câu hỏi
    List<AnswerResponse> getAnswersByQuestionId(Long questionId);
}
