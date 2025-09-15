package com.fawry.movieapp.repository.movie;

import com.fawry.movieapp.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

    List<Movie> findByTitleContainingIgnoreCase(String title);

}
