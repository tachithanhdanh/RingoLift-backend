package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.FeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBack, Long> {
    List<FeedBack> findByUserIdAndLessonId(Long userId, Long lessonId);
}

