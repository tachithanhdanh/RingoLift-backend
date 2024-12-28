package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.Question;
import com.gorgeous.ringolift.models.QuestionType;
import com.gorgeous.ringolift.repositories.LessonQuestionRepository;
import com.gorgeous.ringolift.repositories.QuestionRepository;
import com.gorgeous.ringolift.repositories.QuestionTypeRepository;
import com.gorgeous.ringolift.requests.QuestionRequest;
import com.gorgeous.ringolift.requests.QuestionTypeRequest;
import com.gorgeous.ringolift.responses.LessonQuestionResponse;
import com.gorgeous.ringolift.responses.QuestionResponse;
import com.gorgeous.ringolift.responses.QuestionTypeResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements
        QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionTypeRepository questionTypeRepository;
    private final LessonQuestionRepository lessonQuestionRepository;

    @Override
    public QuestionResponse createQuestion(QuestionRequest questionRequest)
            throws DataNotFoundException {
        QuestionType existingQuestionType = questionTypeRepository.findById(
                        questionRequest.getTypeId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find question type with id " + questionRequest.getTypeId()));
        Question newQuestion = Question.builder()
                .content(questionRequest.getContent())
                .audioUrl(questionRequest.getAudioUrl())
                .hint(questionRequest.getHint())
                .type(existingQuestionType)
                .correctAnswer(questionRequest.getCorrectAnswer()) // Set đáp án đúng
                .build();
        Question savedQuestion = questionRepository.save(newQuestion);
        return QuestionResponse.fromQuestion(savedQuestion);
    }

    @Override
    public QuestionResponse getQuestionById(Long questionId) throws DataNotFoundException {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find question with id " + questionId));
        return QuestionResponse.fromQuestion(question);
    }

    @Override
    public List<QuestionResponse> getQuestionsByTypeId(Long typeId) throws DataNotFoundException {
        return QuestionResponse.fromQuestions(questionRepository.findByTypeId(typeId));
    }

    @Override
    public List<QuestionResponse> getAllQuestions() {
        return QuestionResponse.fromQuestions(questionRepository.findAll());
    }

    @Override
    public QuestionResponse updateQuestion(Long questionId, QuestionRequest questionRequest)
            throws DataNotFoundException {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find question with id " + questionId));
        QuestionType existingQuestionType = questionTypeRepository.findById(
                        questionRequest.getTypeId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find question type with id " + questionRequest.getTypeId()));
        existingQuestion.setContent(questionRequest.getContent());
        existingQuestion.setAudioUrl(questionRequest.getAudioUrl());
        existingQuestion.setHint(questionRequest.getHint());
        existingQuestion.setType(existingQuestionType);
        existingQuestion.setCorrectAnswer(questionRequest.getCorrectAnswer()); // Cập nhật đáp án đúng
        return QuestionResponse.fromQuestion(questionRepository.save(existingQuestion));
    }

    @Override
    public void deleteQuestion(Long questionId) throws DataNotFoundException {
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find question with id " + questionId));
        questionRepository.deleteById(questionId);
    }

    @Override
    public QuestionTypeResponse createQuestionType(QuestionTypeRequest questionTypeRequest) {
        QuestionType questionType = QuestionType.fromString(
                questionTypeRequest.getType().toUpperCase());
        QuestionType savedQuestionType = questionTypeRepository.save(questionType);
        return QuestionTypeResponse.fromQuestionType(savedQuestionType);
    }

    @Override
    public List<QuestionTypeResponse> getAllQuestionTypes() {
        List<QuestionType> questionTypes = questionTypeRepository.findAll();
        return QuestionTypeResponse.fromQuestionTypes(questionTypes);
    }

    @Override
    public QuestionTypeResponse getQuestionTypeById(Long typeId) throws DataNotFoundException {
        QuestionType questionType = questionTypeRepository.findById(typeId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find question type with id " + typeId));
        return QuestionTypeResponse.fromQuestionType(questionType);
    }

    @Override
    public QuestionTypeResponse getQuestionTypeByType(QuestionTypeRequest questionTypeRequest)
            throws DataNotFoundException {
        String type = questionTypeRequest.getType().toUpperCase();
        QuestionType questionType = questionTypeRepository.findByType(type)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find question type " + type));
        return QuestionTypeResponse.fromQuestionType(questionType);
    }

    @Override
    public QuestionTypeResponse updateQuestionType(Long typeId,
            QuestionTypeRequest questionTypeRequest)
            throws DataNotFoundException {
        QuestionType existingQuestionType = questionTypeRepository.findById(typeId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find question type with id " + typeId));
        existingQuestionType.setType(QuestionType.QuestionTypeEnum.valueOf(
                questionTypeRequest.getType().toUpperCase()));
        return QuestionTypeResponse.fromQuestionType(
                questionTypeRepository.save(existingQuestionType));
    }

    @Override
    public void deleteQuestionType(Long typeId) throws DataNotFoundException {
        QuestionType existingQuestionType = questionTypeRepository.findById(typeId)
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find question type with id " + typeId));
        questionTypeRepository.deleteById(typeId);
    }

    @Override
    public List<LessonQuestionResponse> getLessonsByQuestionId(Long questionId) {
        return LessonQuestionResponse.fromLessonQuestionList(
                lessonQuestionRepository.findByQuestionId(questionId));
    }

    @Override
    @Transactional
    public void removeAllLessonsFromQuestion(Long questionId) throws DataNotFoundException {
        lessonQuestionRepository.deleteByQuestionId(questionId);
    }
}
