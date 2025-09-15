package com.fawry.movieapp.model.dto.response;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MovieResponse {

    private int id;

    private String title;

    private String genre;

    private String director;

    private String year;

    private String poster;

}
