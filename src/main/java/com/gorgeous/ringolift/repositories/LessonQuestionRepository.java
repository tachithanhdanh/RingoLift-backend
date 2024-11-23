package com.gorgeous.ringolift.repositories;


import com.gorgeous.ringolift.models.LessonQuestion;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonQuestionRepository extends JpaRepository<LessonQuestion, Long> {

    Optional<LessonQuestion> findByLessonIdAndQuestionId(Long lessonId, Long questionId);

    List<LessonQuestion> findByLessonId(Long lessonId);

    List<LessonQuestion> findByQuestionId(Long questionId);

    void deleteByLessonId(Long lessonId);

    void deleteByQuestionId(Long questionId);
}
