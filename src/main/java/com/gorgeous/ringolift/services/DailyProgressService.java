package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.DailyProgress;
import com.gorgeous.ringolift.requests.DailyProgressRequest;
import com.gorgeous.ringolift.responses.DailyProgressResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DailyProgressService {
    public DailyProgressResponse createDailyProgress(DailyProgressRequest dailyProgressRequest) throws DataNotFoundException;
    public DailyProgressResponse getDailyProgressByUserIdAndCreatedAt(Long userId, LocalDate createdAt) throws DataNotFoundException;
    public DailyProgressResponse getDailyProgressById(Long dailyProgressId) throws DataNotFoundException;
    public List<DailyProgressResponse> getAllDailyProgresses();
    public DailyProgressResponse updateDailyProgress(Long dailyProgressId, DailyProgressRequest dailyProgressRequest) throws DataNotFoundException;
    public void deleteDailyProgress(Long dailyProgressId) throws DataNotFoundException;

}
