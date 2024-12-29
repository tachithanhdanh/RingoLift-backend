package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserAnswer, Long> {

    Optional<UserAnswer> findByIdAndUserIdAndQuestionId(Long id, Long userId, Long questionId);

    List<UserAnswer> findByUserId(Long userId);

    List<UserAnswer> findByQuestionId(Long questionId);

    UserAnswer findByUserIdAndQuestionId(Long userId, Long questionId);
}
