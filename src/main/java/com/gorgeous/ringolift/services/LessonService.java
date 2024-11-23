package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.requests.LessonRequest;
import com.gorgeous.ringolift.responses.LessonQuestionResponse;
import com.gorgeous.ringolift.responses.LessonResponse;
import java.util.List;

public interface LessonService {
    LessonResponse createLesson(LessonRequest lessonRequest) throws DataNotFoundException;

    LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest)
            throws DataNotFoundException;

    LessonResponse getLessonById(Long lessonId) throws DataNotFoundException;

    List<LessonResponse> getLessonsByChapterId(Long chapterId);

    void deleteLesson(Long lessonId) throws DataNotFoundException;

    LessonQuestionResponse addQuestionToLesson(Long lessonId, Long questionId)
            throws DataNotFoundException, DuplicateDataException;

//    LessonQuestionResponse getLessonQuestionById(Long id) throws DataNotFoundException;

    LessonQuestionResponse getLessonQuestionByLessonIdAndQuestionId(Long lessonId, Long questionId)
            throws DataNotFoundException;

    // Delete the row that has the lessonId and questionId
    void removeQuestionFromLesson(Long lessonId, Long questionId) throws DataNotFoundException;

    // Delete all rows that have the lessonId
    void removeAllQuestionsFromLesson(Long lessonId) throws DataNotFoundException;

    List<LessonQuestionResponse> getQuestionsByLessonId(Long lessonId);

}
