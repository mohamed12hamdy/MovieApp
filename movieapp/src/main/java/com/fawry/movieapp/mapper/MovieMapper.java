package com.fawry.movieapp.mapper;

import com.fawry.movieapp.model.dto.request.MovieRequest;
import com.fawry.movieapp.model.dto.response.MovieResponse;
import com.fawry.movieapp.model.entity.Movie;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieMapper {

    private MovieMapper() {
    }

    public static MovieResponse toResponseDto (Movie movie) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .year(movie.getYear())
                .poster(movie.getPoster())
                .build();
    }

    public static Movie toEntity (MovieRequest movieRequest) {
        return Movie.builder()
                .title(movieRequest.getTitle())
                .year(movieRequest.getYear())
                .poster(movieRequest.getPoster())
                .genre(movieRequest.getGenre())
                .director(movieRequest.getDirector())
                .build();
    }


}
