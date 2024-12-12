package com.gorgeous.ringolift.responses;

import com.gorgeous.ringolift.models.Admin;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminResponse extends BaseResponse {

    private Long id;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AdminResponse fromAdmin(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .username(admin.getUsername())
                .createdAt(admin.getCreatedAt())
                .updatedAt(admin.getUpdatedAt())
                .build();
    }
}
