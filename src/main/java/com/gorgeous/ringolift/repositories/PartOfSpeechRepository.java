package com.gorgeous.ringolift.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gorgeous.ringolift.models.PartOfSpeech;

public interface PartOfSpeechRepository extends JpaRepository<PartOfSpeech, Long> {
}
