package com.gorgeous.ringolift.repositories;

import com.gorgeous.ringolift.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
    List<Admin> findAll();
}
