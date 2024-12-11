package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
    boolean existsByNotiType(String notiType);
}
