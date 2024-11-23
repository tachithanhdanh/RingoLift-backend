package com.gorgeous.ringolift.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "books")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "num_of_pages")
    private Integer numOfPages;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "description")
    private String description;

    @Column(name = "cover_image")
    private String coverImage;

    @Column(name = "content_url")
    private String contentUrl;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id", nullable = false)
    private BookGenre genre;

}
