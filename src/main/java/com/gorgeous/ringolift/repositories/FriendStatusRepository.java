package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.FriendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendStatusRepository extends JpaRepository<FriendStatus, Long> {
    Optional<FriendStatus> findByStatusType(String statusType);
}
