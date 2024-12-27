package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Mistake;
import com.gorgeous.ringolift.models.Lesson;
import com.gorgeous.ringolift.models.Question;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.repositories.MistakeRepository;
import com.gorgeous.ringolift.repositories.LessonRepository;
import com.gorgeous.ringolift.repositories.QuestionRepository;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.requests.MistakeRequest;
import com.gorgeous.ringolift.responses.MistakeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MistakeServiceImpl implements MistakeService {
    private final MistakeRepository mistakeRepository;

    private final LessonRepository lessonRepository;

    private final QuestionRepository questionRepository;

    private final UserRepository userRepository;

    @Override
    public MistakeResponse createMistake(MistakeRequest request) throws DataNotFoundException {
        // Lấy Lesson, Question, và User từ cơ sở dữ liệu
        Lesson lesson = lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new DataNotFoundException("Lesson not found with id: " + request.getLessonId()));

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new DataNotFoundException("Question not found with id: " + request.getQuestionId()));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found with id: " + request.getUserId()));

        // Tạo Mistake mới và lưu vào cơ sở dữ liệu
        Mistake mistake = new Mistake();
        mistake.setUser(user);
        mistake.setLesson(lesson);
        mistake.setQuestion(question);
        mistake.setActive(request.getActive());

        Mistake savedMistake = mistakeRepository.save(mistake);
        return mapToResponse(savedMistake);
    }

    @Override
    public MistakeResponse updateMistake(Long id, MistakeRequest request) throws DataNotFoundException {
        // Tìm Mistake theo ID
        Mistake mistake = mistakeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Mistake not found with id: " + id));

        // Cập nhật Lesson và Question
        Lesson lesson = lessonRepository.findById(request.getLessonId())
                .orElseThrow(() -> new DataNotFoundException("Lesson not found with id: " + request.getLessonId()));

        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new DataNotFoundException("Question not found with id: " + request.getQuestionId()));

        mistake.setLesson(lesson);
        mistake.setQuestion(question);
        mistake.setActive(request.getActive());

        Mistake updatedMistake = mistakeRepository.save(mistake);
        return mapToResponse(updatedMistake);
    }

    @Override
    public void deleteMistakeByParams(Long userId, Long lessonId, Long questionId, Long id) throws DataNotFoundException {
        Mistake mistake = mistakeRepository.findByIdAndUserIdAndLessonIdAndQuestionId(id, userId, lessonId, questionId)
                .orElseThrow(() -> new DataNotFoundException("Mistake not found with provided parameters"));

        mistakeRepository.delete(mistake);
    }

    @Override
    public MistakeResponse getMistakeById(Long userId, Long lessonId, Long questionId, Long id) throws DataNotFoundException {
        Mistake mistake = mistakeRepository.findByIdAndUserIdAndLessonIdAndQuestionId(id, userId, lessonId, questionId)
                .orElseThrow(() -> new DataNotFoundException("Mistake not found with provided parameters"));

        return mapToResponse(mistake);
    }

    @Override
    public List<MistakeResponse> getMistakesByLessonAndUserId(Long userId, Long lessonId) throws DataNotFoundException {
        List<Mistake> mistakes = mistakeRepository.findByUserIdAndLessonId(userId, lessonId);
        return mistakes.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private MistakeResponse mapToResponse(Mistake mistake) {
        return MistakeResponse.fromMistake(mistake);
    }
}