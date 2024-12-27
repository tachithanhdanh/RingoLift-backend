package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.Mistake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MistakeRepository extends JpaRepository<Mistake, Long> {

    Optional<Mistake> findByIdAndUserIdAndLessonIdAndQuestionId(Long id, Long userId, Long lessonId, Long questionId);

    List<Mistake> findByUserIdAndLessonId(Long userId, Long lessonId);
}