package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.requests.LessonRequest;
import com.gorgeous.ringolift.responses.LessonResponse;
import java.util.List;

public interface LessonService {
    LessonResponse createLesson(LessonRequest lessonRequest) throws DataNotFoundException;

    LessonResponse updateLesson(Long lessonId, LessonRequest lessonRequest)
            throws DataNotFoundException;

    LessonResponse getLessonById(Long lessonId) throws DataNotFoundException;

    List<LessonResponse> getLessonsByChapterId(Long chapterId);

    void deleteLesson(Long lessonId) throws DataNotFoundException;
}
