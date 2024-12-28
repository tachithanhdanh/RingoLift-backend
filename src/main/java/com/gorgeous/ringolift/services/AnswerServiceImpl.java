package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Answer;
import com.gorgeous.ringolift.models.Question;
import com.gorgeous.ringolift.repositories.AnswerRepository;
import com.gorgeous.ringolift.repositories.QuestionRepository;
import com.gorgeous.ringolift.requests.AnswerRequest;
import com.gorgeous.ringolift.responses.AnswerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Override
    public AnswerResponse createAnswer(AnswerRequest request) throws DataNotFoundException {
        // Load Question từ database
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new DataNotFoundException("Question not found with id: " + request.getQuestionId()));

        // Tạo Answer mới và set các trường
        Answer answer = new Answer();
        answer.setQuestion(question);
        answer.setContent(request.getContent());

        // Lưu Answer và trả về response
        Answer savedAnswer = answerRepository.save(answer);
        return mapToResponse(savedAnswer);
    }

    @Override
    public AnswerResponse updateAnswer(Long answerId, Long questionId, AnswerRequest request)
            throws DataNotFoundException {
        // Tìm Answer hiện có theo ID
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new DataNotFoundException("Answer not found with id: " + answerId));

        // Load Question từ database dựa trên questionId
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new DataNotFoundException("Question not found with id: " + questionId));

        // Cập nhật các trường của Answer
        answer.setQuestion(question);
        answer.setContent(request.getContent());

        // Lưu Answer đã cập nhật
        Answer updatedAnswer = answerRepository.save(answer);
        return mapToResponse(updatedAnswer);
    }

    @Override
    public void deleteAnswer(Long questionId, Long answerId) throws DataNotFoundException {
        // Tìm Question theo ID
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new DataNotFoundException("Question not found with id: " + questionId));

        // Tìm Answer theo ID và đảm bảo nó liên quan đến Question đã cho
        Answer answer = answerRepository.findByIdAndQuestionId(answerId, questionId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Answer not found with id: " + answerId + " for Question ID: " + questionId));

        // Xóa Answer
        answerRepository.delete(answer);
    }

    @Override
    public List<AnswerResponse> getAnswersByQuestionId(Long questionId) {
        // Lấy Answers theo Question ID
        List<Answer> answers = answerRepository.findByQuestionId(questionId);

        // Map mỗi Answer sang AnswerResponse và trả về
        return answers.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Utility method để map Answer entity sang AnswerResponse
    private AnswerResponse mapToResponse(Answer answer) {
        return AnswerResponse.fromAnswer(answer);
    }
}
