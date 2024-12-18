package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.requests.ChapterProgressRequest;
import com.gorgeous.ringolift.requests.LessonProgressRequest;
import com.gorgeous.ringolift.requests.UserRequest;
import com.gorgeous.ringolift.responses.ChapterProgressResponse;
import com.gorgeous.ringolift.responses.LessonProgressResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.responses.UserGenderResponse;
import com.gorgeous.ringolift.responses.UserResponse;
import com.gorgeous.ringolift.services.UserGenderService;
import com.gorgeous.ringolift.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("${api.prefix}/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserGenderService userGenderService;

    // Create a new user
    @PostMapping("")
    public ResponseEntity<ResponseObject> createUser(
            @Valid @RequestBody UserRequest userRequest
    ) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("User created successfully")
                        .status(HttpStatus.CREATED)
                        .data(userResponse)
                        .build()
        );
    }

    // Get all users
    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get all users successfully")
                        .status(HttpStatus.OK)
                        .data(users)
                        .build()
        );
    }

    // Get user by id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getUserById(@PathVariable Long id) throws DataNotFoundException {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get user by id successfully")
                        .status(HttpStatus.OK)
                        .data(userResponse)
                        .build()
        );
    }

    // Update user by id
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequest userRequest
    ) throws DataNotFoundException {
        UserResponse userResponse = userService.updateUser(id, userRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Update user successfully")
                        .status(HttpStatus.OK)
                        .data(userResponse)
                        .build()
        );
    }

    // Delete user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Delete user successfully")
                        .status(HttpStatus.OK)
                        .data(null)
                        .build()
        );
    }

    // Create chapter progress
    // POST localhost:8088/api/v1/users/1/progress/chapter/{chapter_id}
    @PostMapping("/{user_id}/progress/chapter/{chapter_id}")
    public ResponseEntity<ResponseObject> createChapterProgress(
            @PathVariable("user_id") Long userId,
            @PathVariable("chapter_id") Long chapterId,
            @Valid @RequestBody ChapterProgressRequest chapterProgressRequest
    ) throws DataNotFoundException, DuplicateDataException {
        chapterProgressRequest.setChapterId(chapterId);
        chapterProgressRequest.setUserId(userId);
        ChapterProgressResponse chapterProgressResponse = userService.createChapterProgress(chapterProgressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Chapter progress created successfully")
                        .status(HttpStatus.CREATED)
                        .data(chapterProgressResponse)
                        .build()
        );
    }

    // Get chapter progress by user id and chapter id
    // GET localhost:8088/api/v1/users/1/progress/chapter/1
    @GetMapping("/{user_id}/progress/chapter/{chapter_id}")
    public ResponseEntity<ResponseObject> getChapterProgress(
            @PathVariable("user_id") Long userId,
            @PathVariable("chapter_id") Long chapterId
    ) throws DataNotFoundException {
        ChapterProgressResponse chapterProgressResponse = userService.getChapterProgress(userId, chapterId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get chapter progress successfully")
                        .status(HttpStatus.OK)
                        .data(chapterProgressResponse)
                        .build()
        );
    }

    // Get chapter progress by user id
    // GET localhost:8088/api/v1/users/1/progress/chapter
    @GetMapping("/{user_id}/progress/chapter")
    public ResponseEntity<ResponseObject> getChapterProgressByUserId(@PathVariable("user_id") Long userId) {
        List<ChapterProgressResponse> chapterProgressResponseList = userService.getChapterProgressByUserId(userId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get chapter progress by user id successfully")
                        .status(HttpStatus.OK)
                        .data(chapterProgressResponseList)
                        .build()
        );
    }

    // Update chapter progress by user id and chapter id
    // PUT localhost:8088/api/v1/users/1/progress/chapter/1
    @PutMapping("/{user_id}/progress/chapter/{chapter_id}")
    public ResponseEntity<ResponseObject> updateChapterProgress(
            @PathVariable("user_id") Long userId,
            @PathVariable("chapter_id") Long chapterId,
            @Valid @RequestBody ChapterProgressRequest chapterProgressRequest
    ) throws DataNotFoundException {
        ChapterProgressResponse chapterProgressResponse = userService.updateChapterProgress(userId, chapterId, chapterProgressRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Update chapter progress successfully")
                        .status(HttpStatus.OK)
                        .data(chapterProgressResponse)
                        .build()
        );
    }

    // Delete chapter progress by user id and chapter id
    // DELETE localhost:8088/api/v1/users/1/progress/chapter/1
    @DeleteMapping("/{user_id}/progress/chapter/{chapter_id}")
    public ResponseEntity<ResponseObject> deleteChapterProgress(
            @PathVariable("user_id") Long userId,
            @PathVariable("chapter_id") Long chapterId
    ) {
        userService.deleteChapterProgress(userId, chapterId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Delete chapter progress successfully")
                        .status(HttpStatus.OK)
                        .data(null)
                        .build()
        );
    }

    // Delete chapter progress by user id
    // DELETE localhost:8088/api/v1/users/1/progress/chapter
    @DeleteMapping("/{user_id}/progress/chapter")
    public ResponseEntity<ResponseObject> deleteChapterProgressByUserId(@PathVariable("user_id") Long userId) {
        userService.deleteChapterProgressByUserId(userId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Delete chapter progress by user id successfully")
                        .status(HttpStatus.OK)
                        .data(null)
                        .build()
        );
    }

    // Create lesson progress
    // POST localhost:8088/api/v1/users/1/progress/lesson/{lesson_id}
    @PostMapping("/{user_id}/progress/lesson/{lesson_id}")
    public ResponseEntity<ResponseObject> createLessonProgress(
            @PathVariable("user_id") Long userId,
            @PathVariable("lesson_id") Long lessonId,
            @Valid @RequestBody LessonProgressRequest lessonProgressRequest
    ) throws DataNotFoundException, DuplicateDataException {
        lessonProgressRequest.setLessonId(lessonId);
        lessonProgressRequest.setUserId(userId);
        LessonProgressResponse lessonProgressResponse = userService.createLessonProgress(lessonProgressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Lesson progress created successfully")
                        .status(HttpStatus.CREATED)
                        .data(lessonProgressResponse)
                        .build()
        );
    }

    // Get lesson progress by user id and lesson id
    // GET localhost:8088/api/v1/users/1/progress/lesson/1
    @GetMapping("/{user_id}/progress/lesson/{lesson_id}")
    public ResponseEntity<ResponseObject> getLessonProgress(
            @PathVariable("user_id") Long userId,
            @PathVariable("lesson_id") Long lessonId
    ) throws DataNotFoundException {
        LessonProgressResponse lessonProgressResponse = userService.getLessonProgress(userId, lessonId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get lesson progress successfully")
                        .status(HttpStatus.OK)
                        .data(lessonProgressResponse)
                        .build()
        );
    }

    // Get lesson progress by user id
    // GET localhost:8088/api/v1/users/1/progress/lesson
    @GetMapping("/{user_id}/progress/lesson")
    public ResponseEntity<ResponseObject> getLessonProgressByUserId(@PathVariable("user_id") Long userId) {
        List<LessonProgressResponse> lessonProgressResponseList = userService.getLessonProgressByUserId(userId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get lesson progress by user id successfully")
                        .status(HttpStatus.OK)
                        .data(lessonProgressResponseList)
                        .build()
        );
    }

    // Update lesson progress by user id and lesson id
    // PUT localhost:8088/api/v1/users/1/progress/lesson/1
    @PutMapping("/{user_id}/progress/lesson/{lesson_id}")
    public ResponseEntity<ResponseObject> updateLessonProgress(
            @PathVariable("user_id") Long userId,
            @PathVariable("lesson_id") Long lessonId,
            @Valid @RequestBody LessonProgressRequest lessonProgressRequest
    ) throws DataNotFoundException {
        LessonProgressResponse lessonProgressResponse = userService.updateLessonProgress(userId, lessonId, lessonProgressRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Update lesson progress successfully")
                        .status(HttpStatus.OK)
                        .data(lessonProgressResponse)
                        .build()
        );
    }

    // Delete lesson progress by user id and lesson id
    // DELETE localhost:8088/api/v1/users/1/progress/lesson/1
    @DeleteMapping("/{user_id}/progress/lesson/{lesson_id}")
    public ResponseEntity<ResponseObject> deleteLessonProgress(
            @PathVariable("user_id") Long userId,
            @PathVariable("lesson_id") Long lessonId
    ) {
        userService.deleteLessonProgress(userId, lessonId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Delete lesson progress successfully")
                        .status(HttpStatus.OK)
                        .data(null)
                        .build()
        );
    }

    // Delete lesson progress by user id
    // DELETE localhost:8088/api/v1/users/1/progress/lesson
    @DeleteMapping("/{user_id}/progress/lesson")
    public ResponseEntity<ResponseObject> deleteLessonProgressByUserId(@PathVariable("user_id") Long userId) {
        userService.deleteLessonProgressByUserId(userId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Delete lesson progress by user id successfully")
                        .status(HttpStatus.OK)
                        .data(null)
                        .build()
        );
    }

    // Get all user genders
    // GET localhost:8088/api/v1/users/genders
    @GetMapping("/genders")
    public ResponseEntity<ResponseObject> getAllUserGenders() {
        List<UserGenderResponse> userGenders = userGenderService.getAllUserGenders();
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get all user gender successfully")
                        .status(HttpStatus.OK)
                        .data(userGenders)
                        .build()
        );
    }

    // Get user gender by id
    // GET localhost:8088/api/v1/users/genders/{gender_id}
    @GetMapping("/genders/{gender_id}")
    public ResponseEntity<ResponseObject> getUserGenderById(@PathVariable("gender_id") Long id) throws DataNotFoundException {
        UserGenderResponse userGenderResponse = userGenderService.getUserGenderById(id);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get user gender by id successfully")
                        .status(HttpStatus.OK)
                        .data(userGenderResponse)
                        .build()
        );
    }
}
