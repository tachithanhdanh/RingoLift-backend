package com.gorgeous.ringolift.models;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "book_genre")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookGenre extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "genre_type", nullable = false)
    private String genreType;
}
