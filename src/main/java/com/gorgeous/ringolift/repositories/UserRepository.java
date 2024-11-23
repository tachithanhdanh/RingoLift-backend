package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh nếu cần
}
