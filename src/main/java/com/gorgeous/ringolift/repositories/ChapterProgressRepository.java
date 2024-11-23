package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.ChapterProgress;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterProgressRepository extends JpaRepository<ChapterProgress, Long> {

    ChapterProgress findByUserIdAndChapterId(Long userId, Long chapterId);

    List<ChapterProgress> findByChapterId(Long chapterId);

    List<ChapterProgress> findByUserId(Long userId);
}
