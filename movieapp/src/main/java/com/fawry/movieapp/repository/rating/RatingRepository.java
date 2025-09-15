package com.fawry.movieapp.repository.rating;

import com.fawry.movieapp.model.entity.Rate;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository  extends JpaRepository<Rate,Integer> {

    boolean existsByMovieIdAndUserId(Integer userId, Integer movieId);


}
