package com.gorgeous.ringolift.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRequest {
    @JsonProperty("title")
    @Size(min = 1, max = 255, message = "Title is too long")
    private String title;

    @JsonProperty("author")
    @Size(min = 1, max = 255, message = "Author's name is too long")
    private String author;

    @JsonProperty("genre_id")
    private Long genreId;

    @JsonProperty("published_date")
    private LocalDateTime publishedDate;

    @JsonProperty("isbn")
    @Size(min = 1, max = 255, message = "ISBN is too long")
    private String isbn;

    @JsonProperty("num_of_pages")
    private Integer numOfPages;

    @JsonProperty("publisher")
    @Size(min = 1, max = 255, message = "Publisher's name is too long")
    private String publisher;

    @JsonProperty("description")
    private String description;

    @JsonProperty("cover_image")
    @Size(min = 1, max = 255, message = "Cover image url is too long")
    private String coverImage;

    @JsonProperty("content_url")
    @Size(min = 1, max = 255, message = "Content url is too long")
    private String contentUrl;
}
