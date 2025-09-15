package com.fawry.movieapp.service.rating;

import com.fawry.movieapp.model.dto.request.RatingRequest;

import java.util.Map;

public interface RatingService {

    Map<String,String> rateMovie(RatingRequest ratingRequest, Integer userId);


}
