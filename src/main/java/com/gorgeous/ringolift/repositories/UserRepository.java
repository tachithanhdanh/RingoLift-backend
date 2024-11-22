package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Tìm người dùng theo tên đăng nhập
    Optional<User> findByUsername(String username);

    // Tìm người dùng theo email
    Optional<User> findByEmail(String email);

    // Kiểm tra sự tồn tại của tên đăng nhập
    boolean existsByUsername(String username);

    // Kiểm tra sự tồn tại của email
    boolean existsByEmail(String email);
}
