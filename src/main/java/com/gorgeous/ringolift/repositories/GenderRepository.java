package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<Gender, String> {
    // Các phương thức tùy chỉnh (nếu cần)
}
