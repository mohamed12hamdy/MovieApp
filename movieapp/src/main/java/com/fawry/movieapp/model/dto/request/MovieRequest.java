package com.fawry.movieapp.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {

    @NotBlank(message = "title can not be null or empty")
    private String title;

    private String year;

    private String poster;

    private String genre;

    private String director;
}
