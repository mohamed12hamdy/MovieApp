package com.fawry.movieapp.controller.movie;

import com.fawry.movieapp.mapper.MovieMapper;
import com.fawry.movieapp.model.dto.request.MovieRequest;
import com.fawry.movieapp.model.dto.response.MovieResponse;

import com.fawry.movieapp.service.movie.MovieService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movie")

public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<MovieResponse>> getAllMovies () {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/omdb")
    public ResponseEntity<Object>getAllMoviesFromOmdb (@RequestParam String title) {
        return ResponseEntity.ok(movieService.getMovies(title));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getMovieData (@PathVariable int id) {
       return ResponseEntity.ok(movieService.getMovieData(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addMovie")
   public ResponseEntity<MovieResponse> addMovie (@Valid @RequestBody MovieRequest movieRequest) {
         return new ResponseEntity<>(movieService.addMovie(movieRequest), HttpStatus.CREATED);
   }


    @GetMapping("/paged")
    public ResponseEntity<Page<MovieResponse>> getAllMoviesPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(movieService.getAllMoviesPaged(page, size));
    }


    @PreAuthorize("hasRole('USER')")
    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>>searchMovies (@RequestParam String title) {

         return ResponseEntity.ok(movieService.searchMovies(title).stream().
                 map(MovieMapper::toResponseDto).toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Map<String,String>>deleteMovieById (@PathVariable int id) {
        movieService.deleteMovieById(id);
        return ResponseEntity.ok(Map.of(
                "message", "Movie with id " + id + " deleted"
        ));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/batch")
    public ResponseEntity<List<MovieResponse>>addMovies (@Valid @RequestBody List<MovieRequest>movieRequests) {
        return  new ResponseEntity<>
                (movieService.addMovies(movieRequests),HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/batch")
    public ResponseEntity<Map<String,String>> deleteMovies
            (@RequestBody List<Integer>moviesIds) {

        movieService.deleteMoviesByIds(moviesIds);
        return ResponseEntity.ok(Map.of(
                "message", "Movies deleted"
        ));
    }

}
