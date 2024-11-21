package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.Lesson;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByChapterId(Long chapterId);
}
