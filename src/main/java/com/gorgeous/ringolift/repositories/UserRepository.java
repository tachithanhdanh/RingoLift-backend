package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Bạn có thể thêm các phương thức tùy chỉnh nếu cần
    Optional<User> findByUsername(String username);

}
