package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.User;
import com.gorgeous.ringolift.models.Notification;
import com.gorgeous.ringolift.models.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
    @Repository
    public interface UserRepository extends JpaRepository<User, Long> {
    }

    @Repository
    public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
    }
}
