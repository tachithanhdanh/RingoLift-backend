package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId); // Tìm danh sách thông báo dựa trên ID người dùng
}
