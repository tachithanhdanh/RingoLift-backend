package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {

    Goal findByUserId(Long userId);
}
