package com.gorgeous.ringolift.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_gender")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserGender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gender_type", nullable = false, unique = true)
    private String genderType;
}
