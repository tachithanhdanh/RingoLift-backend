package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.models.Question;
import com.gorgeous.ringolift.models.UserAnswer;
import com.gorgeous.ringolift.repositories.UserAnswerRepository;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.repositories.QuestionRepository;
import com.gorgeous.ringolift.requests.UserAnswerRequest;
import com.gorgeous.ringolift.responses.UserAnswerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserAnswerServiceImpl implements UserAnswerService {

    private final UserAnswerRepository userAnswerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Override
    public UserAnswerResponse createUserAnswer(UserAnswerRequest request) throws DataNotFoundException {
        // Tìm User
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + request.getUserId()));

        // Tìm Question
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new DataNotFoundException("Question not found with id: " + request.getQuestionId()));

        // Tạo UserAnswer mới
        UserAnswer userAnswer = new UserAnswer();
        userAnswer.setUser(user);
        userAnswer.setQuestion(question);
        userAnswer.setAnswerText(request.getAnswerText());

        // Lưu vào database
        UserAnswer savedUserAnswer = userAnswerRepository.save(userAnswer);

        // Trả về phản hồi
        return UserAnswerResponse.fromUserAnswer(savedUserAnswer);
    }

    @Override
    public UserAnswerResponse updateUserAnswer(Long id, UserAnswerRequest request) throws DataNotFoundException {
        // Tìm UserAnswer hiện có
        UserAnswer userAnswer = userAnswerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("UserAnswer not found with id: " + id));

        // Kiểm tra User
        if (!userAnswer.getUser().getId().equals(request.getUserId())) {
            throw new DataNotFoundException("UserAnswer không thuộc về user với id: " + request.getUserId());
        }

        // Kiểm tra Question
        if (!userAnswer.getQuestion().getId().equals(request.getQuestionId())) {
            throw new DataNotFoundException("UserAnswer không thuộc về question với id: " + request.getQuestionId());
        }

        // Cập nhật câu trả lời
        userAnswer.setAnswerText(request.getAnswerText());

        // Lưu vào database
        UserAnswer updatedUserAnswer = userAnswerRepository.save(userAnswer);

        // Trả về phản hồi
        return UserAnswerResponse.fromUserAnswer(updatedUserAnswer);
    }

    @Override
    public void deleteUserAnswer(Long id) throws DataNotFoundException {
        // Tìm UserAnswer
        UserAnswer userAnswer = userAnswerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("UserAnswer not found with id: " + id));

        // Xóa UserAnswer
        userAnswerRepository.delete(userAnswer);
    }

    @Override
    public UserAnswerResponse getUserAnswerById(Long id) throws DataNotFoundException {
        // Tìm UserAnswer
        UserAnswer userAnswer = userAnswerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("UserAnswer not found with id: " + id));

        // Trả về phản hồi
        return UserAnswerResponse.fromUserAnswer(userAnswer);
    }

    @Override
    public List<UserAnswerResponse> getUserAnswersByUserId(Long userId) throws DataNotFoundException {
        // Lấy danh sách UserAnswer theo userId
        List<UserAnswer> userAnswers = userAnswerRepository.findByUserId(userId);
        if (userAnswers.isEmpty()) {
            throw new DataNotFoundException("Không tìm thấy UserAnswers cho user với id: " + userId);
        }

        // Chuyển đổi thành danh sách phản hồi
        return userAnswers.stream()
                .map(UserAnswerResponse::fromUserAnswer)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserAnswerResponse> getUserAnswersByQuestionId(Long questionId) throws DataNotFoundException {
        // Lấy danh sách UserAnswer theo questionId
        List<UserAnswer> userAnswers = userAnswerRepository.findByQuestionId(questionId);
        if (userAnswers.isEmpty()) {
            throw new DataNotFoundException("Không tìm thấy UserAnswers cho question với id: " + questionId);
        }

        // Chuyển đổi thành danh sách phản hồi
        return userAnswers.stream()
                .map(UserAnswerResponse::fromUserAnswer)
                .collect(Collectors.toList());
    }
}
