package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordRepository extends JpaRepository<Word, Long> {
    List<Word> findByTopicId(Long topicId);
}
