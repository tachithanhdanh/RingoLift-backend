package com.gorgeous.ringolift.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "goals")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Goal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "time_spent")
    private Integer timeSpent;

    @Column(name = "lesson_count")
    private Integer lessonCount;

    @Column(name = "word_count")
    private Integer wordCount;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    // The createdAt and updatedAt fields are inherited from BaseEntity
}
