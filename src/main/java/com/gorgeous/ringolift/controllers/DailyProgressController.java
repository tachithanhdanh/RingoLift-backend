package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.DailyProgress;
import com.gorgeous.ringolift.requests.DailyProgressRequest;
import com.gorgeous.ringolift.responses.DailyProgressResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.DailyProgressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("${api.prefix}/daily-progresses")
@RequiredArgsConstructor
public class DailyProgressController {

    private final DailyProgressService dailyProgressService;

    // Create a new daily progress
    // POST http://localhost:8088/api/v1/daily-progresses
    @PostMapping("")
    public ResponseEntity<ResponseObject> createDailyProgress(
            @RequestBody DailyProgressRequest dailyProgressRequest
    ) throws DataNotFoundException {
        DailyProgressResponse dailyProgressResponse = dailyProgressService.createDailyProgress(dailyProgressRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Create daily progress successfully")
                        .status(HttpStatus.CREATED)
                        .data(dailyProgressResponse)
                        .build()
        );
    }

    // Get all daily progresses
    // GET http://localhost:8088/api/v1/daily-progresses
    @GetMapping("")
    public ResponseEntity<ResponseObject> getDailyProgress(
            @RequestParam(value = "user_id", required = false) Long userId,
            @RequestParam(value = "created_at", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdAt
    ) throws DataNotFoundException {
        if (userId != null && createdAt != null) {
            // Nếu có cả user_id và created_at, trả về DailyProgress cụ thể
            DailyProgressResponse dailyProgressResponse = dailyProgressService.getDailyProgressByUserIdAndCreatedAt(userId, createdAt);
            return ResponseEntity.status(HttpStatus.OK).body(
                    ResponseObject.builder()
                            .message("Get daily progress by user id and created at successfully")
                            .status(HttpStatus.OK)
                            .data(dailyProgressResponse)
                            .build()
            );
        } else {
            // Nếu không có user_id hoặc created_at, trả về danh sách tất cả DailyProgresses
            List<DailyProgressResponse> dailyProgresses = dailyProgressService.getAllDailyProgresses();
            return ResponseEntity.status(HttpStatus.OK).body(
                    ResponseObject.builder()
                            .message("Get list of daily progresses successfully")
                            .status(HttpStatus.OK)
                            .data(dailyProgresses)
                            .build()
            );
        }
    }

    // Get a daily progress
    // GET http://localhost:8088/api/v1/daily-progresses/1
    @GetMapping("/{id}")
    public ResponseEntity<ResponseObject> getDailyProgressById(
            @PathVariable("id") Long id
    ) throws DataNotFoundException {
        DailyProgressResponse dailyProgressResponse = dailyProgressService.getDailyProgressById(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Get daily progress by id successfully")
                        .status(HttpStatus.OK)
                        .data(dailyProgressResponse)
                        .build()
        );
    }

    // Update daily progress
    // PUT http://localhost:8088/api/v1/daily-progresses/1
    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject> updateDailyProgress(
            @Valid @PathVariable Long id,
            @Valid @RequestBody DailyProgressRequest dailyProgressRequest
    ) throws DataNotFoundException {
        DailyProgressResponse dailyProgressResponse = dailyProgressService.updateDailyProgress(id, dailyProgressRequest);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Update daily progress successfully")
                        .status(HttpStatus.OK)
                        .data(dailyProgressResponse)
                        .build()
        );
    }

    // Delete daily progress by id
    // DELETE http://localhost:8088/api/v1/daily-progresses/1
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject> deleteDailyProgress(
            @PathVariable Long id
    ) throws DataNotFoundException {
        dailyProgressService.deleteDailyProgress(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .message("Delete a daily progress successfully")
                        .status(HttpStatus.OK)
                        .build()
        );
    }
}
