package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUserIdAndLessonId(Long userId, Long lessonId);
}

