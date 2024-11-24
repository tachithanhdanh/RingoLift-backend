package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.QuestionType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {
    Optional<QuestionType> findByType(String type);

}
