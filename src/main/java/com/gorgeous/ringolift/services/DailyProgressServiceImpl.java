package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.models.DailyProgress;
import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.repositories.DailyProgressRepository;
import com.gorgeous.ringolift.repositories.UserRepository;
import com.gorgeous.ringolift.requests.DailyProgressRequest;
import com.gorgeous.ringolift.responses.DailyProgressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyProgressServiceImpl implements DailyProgressService {

    private final DailyProgressRepository dailyProgressRepository;
    private final UserRepository userRepository;

    @Override
    public DailyProgressResponse createDailyProgress(
            DailyProgressRequest dailyProgressRequest
    ) throws DataNotFoundException {
        // Get user
        User user = userRepository.findById(dailyProgressRequest.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        // Create daily progress
        DailyProgress dailyProgress = DailyProgress.builder()
                .user(user)
                .timeSpent(dailyProgressRequest.getTimeSpent())
                .lessonCount(dailyProgressRequest.getLessonCount())
                .wordCount(dailyProgressRequest.getWordCount())
                .build();

        return DailyProgressResponse.fromDailyProgress(dailyProgressRepository.save(dailyProgress));
    }

    @Override
    public DailyProgressResponse getDailyProgressById(Long dailyProgressId) throws DataNotFoundException {
        return dailyProgressRepository.findById(dailyProgressId)
                .map(DailyProgressResponse::fromDailyProgress)
                .orElseThrow(() -> new DataNotFoundException("DailyProgress not found"));
    }

    @Override
    public DailyProgressResponse getDailyProgressByUserIdAndCreatedAt(Long userId, LocalDate createdAt) throws DataNotFoundException {
        DailyProgress dailyProgress = dailyProgressRepository.findByUserIdAndCreatedAt(userId, createdAt);
        if (dailyProgress == null) {
            // Get user
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new DataNotFoundException("User not found"));

            // Get daily progress
            dailyProgress = DailyProgress.builder()
                    .user(user)
                    .timeSpent(0)
                    .lessonCount(0)
                    .wordCount(0)
                    .build();
            dailyProgressRepository.save(dailyProgress);
        }
        return DailyProgressResponse.fromDailyProgress(dailyProgress);
    }

    @Override
    public List<DailyProgressResponse> getAllDailyProgresses() {
        return dailyProgressRepository.findAll().stream().map(DailyProgressResponse::fromDailyProgress).toList();
    }

    @Override
    public DailyProgressResponse updateDailyProgress(Long dailyProgressId, DailyProgressRequest dailyProgressRequest) throws DataNotFoundException {
        DailyProgress dailyProgress = dailyProgressRepository.findById(dailyProgressId)
                .orElseThrow(() -> new DataNotFoundException("DailyProgress not found"));

        User user = userRepository.findById(dailyProgressRequest.getUserId())
                .orElseThrow(() -> new DataNotFoundException("User not found"));

        dailyProgress.setUser(user);
        dailyProgress.setTimeSpent(dailyProgressRequest.getTimeSpent());
        dailyProgress.setLessonCount(dailyProgressRequest.getLessonCount());
        dailyProgress.setWordCount(dailyProgressRequest.getWordCount());

        return DailyProgressResponse.fromDailyProgress(dailyProgressRepository.save(dailyProgress));
    }

    @Override
    public void deleteDailyProgress(Long dailyProgressId) throws DataNotFoundException {
        if (!dailyProgressRepository.existsById(dailyProgressId)) {
            throw new DataNotFoundException("DailyProgress not found");
        }
        dailyProgressRepository.deleteById(dailyProgressId);
    }
}
