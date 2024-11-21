package com.gorgeous.ringolift.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "chapters")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Chapter extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "description")
    private String description;

    // Define the relationship between Chapter and Lesson
    // A chapter can have multiple lessons
    // cascade = CascadeType.ALL: If a chapter is deleted, all its lessons will be deleted
    // fetch = FetchType.LAZY: Lessons will be loaded lazily
    // orphanRemoval = true: If a lesson is removed from the lessons list, it will be deleted from the database
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Lesson> lessons;
}
