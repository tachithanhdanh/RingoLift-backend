package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
