package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.LessonProgress;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonProgressRepository extends JpaRepository<LessonProgress, Long> {

    Optional<LessonProgress> findByUserIdAndLessonId(Long userId, Long lessonId);

    List<LessonProgress> findByLessonId(Long lessonId);

    List<LessonProgress> findByUserId(Long userId);

    void deleteByUserIdAndLessonId(Long userId, Long lessonId);

    void deleteByUserId(Long userId);
}
