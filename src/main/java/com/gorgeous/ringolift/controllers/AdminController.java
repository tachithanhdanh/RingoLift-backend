package com.gorgeous.ringolift.controllers;

import com.gorgeous.ringolift.exceptions.DataNotFoundException;
import com.gorgeous.ringolift.exceptions.DuplicateDataException;
import com.gorgeous.ringolift.requests.LoginRequest;
import com.gorgeous.ringolift.requests.AdminRegisterRequest;
import com.gorgeous.ringolift.responses.AdminResponse;
import com.gorgeous.ringolift.responses.ResponseObject;
import com.gorgeous.ringolift.services.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("${api.prefix}/admins")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Đăng ký Admin
//    @PostMapping("/register")
    public ResponseEntity<ResponseObject> registerAdmin(
            @Valid @RequestBody AdminRegisterRequest adminRegisterRequest
    ) throws DuplicateDataException {
        AdminResponse adminResponse = adminService.registerAdmin(adminRegisterRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseObject.builder()
                        .message("Admin registered successfully")
                        .status(HttpStatus.CREATED)
                        .data(adminResponse)
                        .build()
        );
    }

    // Đăng nhập Admin
//    @PostMapping("/login")
    public ResponseEntity<ResponseObject> loginAdmin(
            @Valid @RequestBody LoginRequest adminLoginRequest
    ) throws DataNotFoundException {
        AdminResponse adminResponse = adminService.loginAdmin(adminLoginRequest);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Admin logged in successfully")
                        .status(HttpStatus.OK)
                        .data(adminResponse)
                        .build()
        );
    }

    // Lấy danh sách tất cả Admin
//    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllAdmins() {
        List<AdminResponse> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Get all admins successfully")
                        .status(HttpStatus.OK)
                        .data(admins)
                        .build()
        );
    }

    // Xóa Admin theo ID
//    @DeleteMapping("/{adminId}")
    public ResponseEntity<ResponseObject> deleteAdmin(@PathVariable Long adminId) throws DataNotFoundException {
        adminService.deleteAdmin(adminId);
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .message("Admin deleted successfully")
                        .status(HttpStatus.OK)
                        .data(null)
                        .build()
        );
    }
}
