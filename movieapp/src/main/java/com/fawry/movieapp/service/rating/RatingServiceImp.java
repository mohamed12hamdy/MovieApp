package com.fawry.movieapp.service.rating;

import com.fawry.movieapp.exception.movie.MovieNotFoundException;
import com.fawry.movieapp.exception.user.UserNotFoundException;
import com.fawry.movieapp.model.dto.request.RatingRequest;
import com.fawry.movieapp.model.entity.Movie;
import com.fawry.movieapp.model.entity.Rate;
import com.fawry.movieapp.model.entity.User;
import com.fawry.movieapp.repository.movie.MovieRepository;
import com.fawry.movieapp.repository.rating.RatingRepository;
import com.fawry.movieapp.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RatingServiceImp implements RatingService {

    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;


    public RatingServiceImp(RatingRepository ratingRepository, MovieRepository movieRepository,
                            UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public Map<String, String> rateMovie(RatingRequest ratingRequest, Integer userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Movie movie = movieRepository.findById(ratingRequest.getMovieId())
                .orElseThrow(() -> new MovieNotFoundException("Movie not found"));

        if (ratingRepository.existsByMovieIdAndUserId(movie.getId(), user.getId())) {
            return Map.of("message", "You have rated this movie before");
        }

        Rate rate = Rate.builder()
                .movie(movie)
                .user(user)
                .rate(ratingRequest.getRatingValue())
                .build();

        ratingRepository.save(rate);
        return Map.of("message", "Rating is added Successfully");
    }
}