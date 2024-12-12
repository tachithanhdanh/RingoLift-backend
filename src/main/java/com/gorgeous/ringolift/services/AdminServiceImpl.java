package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.models.Admin;
import com.gorgeous.ringolift.repositories.AdminRepository;
import com.gorgeous.ringolift.requests.AdminLoginRequest;
import com.gorgeous.ringolift.requests.AdminRegisterRequest;
import com.gorgeous.ringolift.responses.AdminResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;

    @Override
    public AdminResponse registerAdmin(AdminRegisterRequest adminRegisterRequest) throws DuplicateDataException {
        if (adminRepository.findByUsername(adminRegisterRequest.getUsername()).isPresent()) {
            throw new DuplicateDataException("Username already exists.");
        }

        Admin admin = Admin.builder()
                .username(adminRegisterRequest.getUsername())
                .password(adminRegisterRequest.getPassword())
                .build();

        Admin savedAdmin = adminRepository.save(admin);
        return AdminResponse.fromAdmin(savedAdmin);
    }

    @Override
    public AdminResponse loginAdmin(AdminLoginRequest adminLoginRequest) throws DataNotFoundException {
        Admin admin = adminRepository.findByUsername(adminLoginRequest.getUsername())
                .orElseThrow(() -> new DataNotFoundException("Admin not found with username " + adminLoginRequest.getUsername()));

        if (!adminLoginRequest.getPassword().equals(admin.getPassword())) {
            throw new DataNotFoundException("Invalid password.");
        }

        return AdminResponse.fromAdmin(admin);
    }

    @Override
    public List<AdminResponse> getAllAdmins() {
        return adminRepository.findAll().stream()
                .map(AdminResponse::fromAdmin)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAdmin(Long adminId) throws DataNotFoundException {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new DataNotFoundException("Admin not found with id " + adminId));
        adminRepository.delete(admin);
    }
}
