package com.gorgeous.ringolift.models;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "daily_progress")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DailyProgress extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_spent")
    private Integer timeSpent;

    @Column(name = "lesson_count")
    private Integer lessonCount;

    @Column(name = "word_count")
    private Integer wordCount;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
}
