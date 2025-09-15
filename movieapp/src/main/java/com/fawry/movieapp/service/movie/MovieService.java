package com.fawry.movieapp.service.movie;

import com.fawry.movieapp.model.dto.request.MovieRequest;
import com.fawry.movieapp.model.dto.response.MovieResponse;
import com.fawry.movieapp.model.entity.Movie;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MovieService {

     List<MovieResponse> getAllMovies();


     Object getMovies(String title);

     MovieResponse getMovieData(int id);


     Page<MovieResponse> getAllMoviesPaged(int page, int size);

     List<Movie> searchMovies(String title);

     MovieResponse addMovie(MovieRequest movieRequest);


     void deleteMovieById(int id);

     List<MovieResponse> addMovies(@Valid List<MovieRequest> movieRequests);

     void deleteMoviesByIds(List<Integer>moviesIds);

}
