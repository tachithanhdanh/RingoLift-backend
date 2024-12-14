package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.models.Chapter;
import com.gorgeous.ringolift.models.ChapterProgress;
import com.gorgeous.ringolift.models.Goal;
import com.gorgeous.ringolift.models.Lesson;
import com.gorgeous.ringolift.models.LessonProgress;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.models.UserGender;
import com.gorgeous.ringolift.repositories.ChapterProgressRepository;
import com.gorgeous.ringolift.repositories.ChapterRepository;
import com.gorgeous.ringolift.repositories.GoalRepository;
import com.gorgeous.ringolift.repositories.LessonProgressRepository;
import com.gorgeous.ringolift.repositories.LessonRepository;
import com.gorgeous.ringolift.repositories.UserGenderRepository;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.requests.ChapterProgressRequest;
import com.gorgeous.ringolift.requests.LessonProgressRequest;
import com.gorgeous.ringolift.requests.UserRequest;
import com.gorgeous.ringolift.responses.ChapterProgressResponse;
import com.gorgeous.ringolift.responses.LessonProgressResponse;
import com.gorgeous.ringolift.responses.UserResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserGenderRepository userGenderRepository;
    private final GoalRepository goalRepository;
    private final LessonRepository lessonRepository;
    private final ChapterRepository chapterRepository;
    private final LessonProgressRepository lessonProgressRepository;
    private final ChapterProgressRepository chapterProgressRepository;

    @Override
    public Optional<UserResponse> getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(UserResponse::fromUser);
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        UserGender gender = null;
        if (userRequest.getGenderId() != null) {
            gender = userGenderRepository.findById(userRequest.getGenderId()).orElse(null);
        }

        Goal goal = null;
        if (userRequest.getGoalId() != null) {
            goal = goalRepository.findById(userRequest.getGoalId()).orElse(null);
        }

        User user = User.builder().username(userRequest.getUsername()).email(userRequest.getEmail())
                .firstName(userRequest.getFirstName()).lastName(userRequest.getLastName())
                .dateOfBirth(userRequest.getDateOfBirth()).gender(gender)
                .picture(userRequest.getPicture()).goal(goal).password(userRequest.getPassword())
                .isPublic(userRequest.getIsPublic()).googleId(userRequest.getGoogleId())
                .accessToken(userRequest.getAccessToken()).build();

        return UserResponse.fromUser(userRepository.save(user));
    }

    @Override
    public UserResponse getUserById(Long id) throws DataNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id " + id));
        return UserResponse.fromUser(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(UserResponse::fromUser).toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest userRequest) throws DataNotFoundException {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id " + id));

        if (userRequest.getUsername() != null) {
            existingUser.setUsername(userRequest.getUsername());
        }
        if (userRequest.getEmail() != null) {
            existingUser.setEmail(userRequest.getEmail());
        }
        existingUser.setFirstName(userRequest.getFirstName());
        existingUser.setLastName(userRequest.getLastName());
        existingUser.setDateOfBirth(userRequest.getDateOfBirth());

        if (userRequest.getGenderId() != null) {
            UserGender gender = userGenderRepository.findById(userRequest.getGenderId())
                    .orElse(null);
            existingUser.setGender(gender);
        }

        existingUser.setPicture(userRequest.getPicture());

        if (userRequest.getGoalId() != null) {
            Goal goal = goalRepository.findById(userRequest.getGoalId()).orElse(null);
            existingUser.setGoal(goal);
        }

        existingUser.setPassword(userRequest.getPassword());
        existingUser.setIsPublic(userRequest.getIsPublic());
        existingUser.setGoogleId(userRequest.getGoogleId());
        existingUser.setAccessToken(userRequest.getAccessToken());

        return UserResponse.fromUser(userRepository.save(existingUser));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public ChapterProgressResponse createChapterProgress(
            ChapterProgressRequest chapterProgressRequest)
            throws DuplicateDataException, DataNotFoundException {
        ChapterProgress existingChapterProgress = chapterProgressRepository.findByUserIdAndChapterId(
                        chapterProgressRequest.getUserId(), chapterProgressRequest.getChapterId())
                .orElse(null);
        if (existingChapterProgress != null) {
            throw new DuplicateDataException("Chapter progress already exists");
        }

        User existingUser = userRepository.findById(chapterProgressRequest.getUserId()).orElseThrow(
                () -> new DataNotFoundException(
                        "Cannot find user with id " + chapterProgressRequest.getUserId()));
        Chapter existingChapter = chapterRepository.findById(chapterProgressRequest.getChapterId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find chapter with id " + chapterProgressRequest.getChapterId()));

        ChapterProgress newChapterProgress = ChapterProgress.builder()
                .unlocked(chapterProgressRequest.getUnlocked()).user(existingUser)
                .chapter(existingChapter).build();
        return ChapterProgressResponse.fromChapterProgress(
                chapterProgressRepository.save(newChapterProgress));
    }

    @Override
    public ChapterProgressResponse getChapterProgress(Long userId, Long chapterId)
            throws DataNotFoundException {
        userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id " + userId));
        chapterRepository.findById(chapterId).orElseThrow(
                () -> new DataNotFoundException("Cannot find chapter with id " + chapterId));
        return ChapterProgressResponse.fromChapterProgress(
                chapterProgressRepository.findByUserIdAndChapterId(userId, chapterId).orElseThrow(
                        () -> new DataNotFoundException(
                                "Cannot find chapter progress with user id " + userId
                                        + " and chapter id " + chapterId)));
    }

    @Override
    public List<ChapterProgressResponse> getChapterProgressByUserId(Long userId) {
        return ChapterProgressResponse.fromChapterProgressList(
                chapterProgressRepository.findByUserId(userId));
    }

    @Override
    public ChapterProgressResponse updateChapterProgress(Long userId, Long chapterId,
            ChapterProgressRequest chapterProgressRequest) throws DataNotFoundException {
        userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id " + userId));
        chapterRepository.findById(chapterId).orElseThrow(
                () -> new DataNotFoundException("Cannot find chapter with id " + chapterId));
        ChapterProgress existingChapterProgress = chapterProgressRepository.findByUserIdAndChapterId(
                userId, chapterId).orElseThrow(() -> new DataNotFoundException(
                "Cannot find chapter progress with user id " + userId + " and chapter id "
                        + chapterId));
        existingChapterProgress.setUnlocked(chapterProgressRequest.getUnlocked());
        return ChapterProgressResponse.fromChapterProgress(
                chapterProgressRepository.save(existingChapterProgress));
    }

    @Transactional
    @Override
    public void deleteChapterProgress(Long userId, Long chapterId) {
        chapterProgressRepository.deleteByUserIdAndChapterId(userId, chapterId);
    }

    @Transactional
    @Override
    public void deleteChapterProgressByUserId(Long userId) {
        chapterProgressRepository.deleteByUserId(userId);
    }

    @Override
    public LessonProgressResponse createLessonProgress(LessonProgressRequest lessonProgressRequest)
            throws DuplicateDataException, DataNotFoundException {
        LessonProgress existingLessonProgress = lessonProgressRepository.findByUserIdAndLessonId(
                        lessonProgressRequest.getUserId(), lessonProgressRequest.getLessonId())
                .orElse(null);
        if (existingLessonProgress != null) {
            throw new DuplicateDataException("Lesson progress already exists");
        }
        User existingUser = userRepository.findById(lessonProgressRequest.getUserId()).orElseThrow(
                () -> new DataNotFoundException(
                        "Cannot find user with id " + lessonProgressRequest.getUserId()));
        Lesson existingLesson = lessonRepository.findById(lessonProgressRequest.getLessonId())
                .orElseThrow(() -> new DataNotFoundException(
                        "Cannot find lesson with id " + lessonProgressRequest.getLessonId()));
        LessonProgress newLessonProgress = LessonProgress.builder()
                .correctCount(lessonProgressRequest.getCorrectCount())
                .incorrectCount(lessonProgressRequest.getIncorrectCount())
                .timeSpent(lessonProgressRequest.getTimeSpent())
                .user(existingUser)
                .lesson(existingLesson)
                .build();
        return LessonProgressResponse.fromLessonProgress(
                lessonProgressRepository.save(newLessonProgress));
    }

    @Override
    public LessonProgressResponse getLessonProgress(Long userId, Long lessonId)
            throws DataNotFoundException {
        userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id " + userId));
        lessonRepository.findById(lessonId).orElseThrow(
                () -> new DataNotFoundException("Cannot find lesson with id " + lessonId));
        return LessonProgressResponse.fromLessonProgress(
                lessonProgressRepository.findByUserIdAndLessonId(userId, lessonId).orElseThrow(
                        () -> new DataNotFoundException(
                                "Cannot find lesson progress with user id " + userId
                                        + " and lesson id " + lessonId)));
    }

    @Override
    public List<LessonProgressResponse> getLessonProgressByUserId(Long userId) {
        return LessonProgressResponse.fromLessonProgressList(
                lessonProgressRepository.findByUserId(userId));
    }

    @Override
    public LessonProgressResponse updateLessonProgress(Long userId, Long lessonId,
            LessonProgressRequest lessonProgressRequest) throws DataNotFoundException {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new DataNotFoundException("Cannot find user with id " + userId));
        Lesson existingLesson = lessonRepository.findById(lessonId).orElseThrow(
                () -> new DataNotFoundException("Cannot find lesson with id " + lessonId));
        LessonProgress existingLessonProgress = lessonProgressRepository.findByUserIdAndLessonId(
                userId, lessonId).orElseThrow(() -> new DataNotFoundException(
                "Cannot find lesson progress with user id " + userId + " and lesson id "
                        + lessonId));
        existingLessonProgress.setCorrectCount(lessonProgressRequest.getCorrectCount());
        existingLessonProgress.setIncorrectCount(lessonProgressRequest.getIncorrectCount());
        existingLessonProgress.setTimeSpent(lessonProgressRequest.getTimeSpent());
        existingLessonProgress.setLesson(existingLesson);
        existingLessonProgress.setUser(existingUser);
        return LessonProgressResponse.fromLessonProgress(
                lessonProgressRepository.save(existingLessonProgress));
    }

    @Transactional
    @Override
    public void deleteLessonProgress(Long userId, Long lessonId) {
        lessonProgressRepository.deleteByUserIdAndLessonId(userId, lessonId);
    }

    @Transactional
    @Override
    public void deleteLessonProgressByUserId(Long userId) {
        lessonProgressRepository.deleteByUserId(userId);
    }
}
