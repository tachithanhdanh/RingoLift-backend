package com.gorgeous.ringolift.models;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "word_status")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordStatus extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_type")
    private String statusType;
}
