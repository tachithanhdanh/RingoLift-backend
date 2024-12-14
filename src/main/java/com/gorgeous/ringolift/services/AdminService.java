package com.gorgeous.ringolift.services;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.requests.LoginRequest;
import com.gorgeous.ringolift.requests.AdminRegisterRequest;
import com.gorgeous.ringolift.responses.AdminResponse;

import java.util.List;

public interface AdminService {
    AdminResponse registerAdmin(AdminRegisterRequest adminRegisterRequest) throws DuplicateDataException;
    AdminResponse loginAdmin(LoginRequest adminLoginRequest) throws DataNotFoundException;
    List<AdminResponse> getAllAdmins();
    void deleteAdmin(Long adminId) throws DataNotFoundException;
}
