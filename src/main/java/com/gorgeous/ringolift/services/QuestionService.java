package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.QuestionRequest;
import com.gorgeous.ringolift.requests.QuestionTypeRequest;
import com.gorgeous.ringolift.responses.LessonQuestionResponse;
import com.gorgeous.ringolift.responses.QuestionResponse;
import com.gorgeous.ringolift.responses.QuestionTypeResponse;
import java.util.List;

public interface QuestionService {

    QuestionResponse createQuestion(QuestionRequest questionRequest) throws DataNotFoundException;

    QuestionResponse getQuestionById(Long questionId) throws DataNotFoundException;

    List<QuestionResponse> getQuestionsByTypeId(Long typeId) throws DataNotFoundException;

    List<QuestionResponse> getAllQuestions();

    QuestionResponse updateQuestion(Long questionId, QuestionRequest questionRequest)
            throws DataNotFoundException;

    void deleteQuestion(Long questionId) throws DataNotFoundException;

    QuestionTypeResponse createQuestionType(QuestionTypeRequest questionTypeRequest);

    List<QuestionTypeResponse> getAllQuestionTypes();

    QuestionTypeResponse getQuestionTypeById(Long typeId) throws DataNotFoundException;

    QuestionTypeResponse getQuestionTypeByType(QuestionTypeRequest questionTypeRequest)
            throws DataNotFoundException;

    QuestionTypeResponse updateQuestionType(Long typeId, QuestionTypeRequest questionTypeRequest)
            throws DataNotFoundException;

    void deleteQuestionType(Long typeId) throws DataNotFoundException;

    List<LessonQuestionResponse> getLessonsByQuestionId(Long questionId);

    // Delete all rows that have the questionId
    void removeAllLessonsFromQuestion(Long questionId) throws DataNotFoundException;
}
