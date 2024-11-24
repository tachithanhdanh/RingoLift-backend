package com.gorgeous.ringolift.models;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "word_progress")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WordProgress extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "word_id", referencedColumnName = "id", nullable = false)
    private Word word;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private WordStatus status;

    @Column(name = "note")
    private String note;
}
