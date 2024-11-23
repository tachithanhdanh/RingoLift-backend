package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.Question;
import com.gorgeous.ringolift.models.QuestionType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByTypeId(Long typeId);

}
