package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.Book;
import lombok.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse extends BaseResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("author")
    private String author;

    @JsonProperty("genre_id")
    private Long genreId;

    @JsonProperty("published_date")
    private LocalDateTime publishedDate;

    @JsonProperty("isbn")
    private String isbn;

    @JsonProperty("num_of_pages")
    private Integer numOfPages;

    @JsonProperty("publisher")
    private String publisher;

    @JsonProperty("description")
    private String description;

    @JsonProperty("cover_image")
    private String coverImage;

    @JsonProperty("content_url")
    private String contentUrl;

    public static BookResponse fromBook(Book book) {
        BookResponse bookResponse = BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genreId(book.getGenre().getId())
                .publishedDate(book.getPublishedDate())
                .isbn(book.getIsbn())
                .numOfPages(book.getNumOfPages())
                .publisher(book.getPublisher())
                .description(book.getDescription())
                .coverImage(book.getCoverImage())
                .contentUrl(book.getContentUrl())
                .build();
        bookResponse.setCreatedAt(book.getCreatedAt());
        bookResponse.setUpdatedAt(book.getUpdatedAt());
        return bookResponse;
    }
}
