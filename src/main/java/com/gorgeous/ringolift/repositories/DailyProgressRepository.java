package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.DailyProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DailyProgressRepository extends JpaRepository<DailyProgress, Long> {

    @Query("SELECT dp FROM DailyProgress dp WHERE dp.user.id = :userId AND DATE(dp.createdAt) = :createdAt")
    DailyProgress findByUserIdAndCreatedAt(Long userId, LocalDate createdAt);
}
