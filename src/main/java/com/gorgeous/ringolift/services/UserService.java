package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.requests.ChapterProgressRequest;
import com.gorgeous.ringolift.requests.LessonProgressRequest;
import com.gorgeous.ringolift.requests.UserRequest;
import com.gorgeous.ringolift.responses.ChapterProgressResponse;
import com.gorgeous.ringolift.responses.LessonProgressResponse;
import com.gorgeous.ringolift.responses.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
    UserResponse getUserById(Long id) throws DataNotFoundException;
    List<UserResponse> getAllUsers();
    UserResponse updateUser(Long id, UserRequest userRequest) throws DataNotFoundException;
    void deleteUser(Long id);

    ChapterProgressResponse createChapterProgress(ChapterProgressRequest chapterProgressRequest)
            throws DuplicateDataException, DataNotFoundException;

    ChapterProgressResponse getChapterProgress(Long userId, Long chapterId) throws DataNotFoundException;

    List<ChapterProgressResponse> getChapterProgressByUserId(Long userId);

    ChapterProgressResponse updateChapterProgress(Long userId, Long chapterId, ChapterProgressRequest chapterProgressRequest) throws DataNotFoundException;

    void deleteChapterProgress(Long userId, Long chapterId);

    void deleteChapterProgressByUserId(Long userId);

    LessonProgressResponse createLessonProgress(LessonProgressRequest lessonProgressRequest)
            throws DuplicateDataException, DataNotFoundException;

    LessonProgressResponse getLessonProgress(Long userId, Long lessonId) throws DataNotFoundException;

    List<LessonProgressResponse> getLessonProgressByUserId(Long userId);

    LessonProgressResponse updateLessonProgress(Long userId, Long lessonId, LessonProgressRequest lessonProgressRequest) throws DataNotFoundException;

    void deleteLessonProgress(Long userId, Long lessonId);

    void deleteLessonProgressByUserId(Long userId);
}
