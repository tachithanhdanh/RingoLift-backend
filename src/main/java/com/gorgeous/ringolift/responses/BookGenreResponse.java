package com.gorgeous.ringolift.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gorgeous.ringolift.models.BookGenre;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookGenreResponse extends BaseResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("genre_type")
    private String genreType;

    public static BookGenreResponse fromBookGenre(BookGenre bookGenre) {
        BookGenreResponse bookGenreResponse = BookGenreResponse.builder()
                .id(bookGenre.getId())
                .genreType(bookGenre.getGenreType())
                .build();
        bookGenreResponse.setCreatedAt(bookGenre.getCreatedAt());
        bookGenreResponse.setUpdatedAt(bookGenre.getUpdatedAt());
        return bookGenreResponse;
    }
}
