package com.fawry.movieapp.controller.rate;

import com.fawry.movieapp.model.dto.request.RatingRequest;
import com.fawry.movieapp.service.rating.RatingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rate")
public class RateController {

    private final RatingService ratingService;

    public RateController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> addRate(
            @Valid @RequestBody RatingRequest ratingRequest,
            @RequestAttribute("userId") Integer userId) {

        return new ResponseEntity<>(
                ratingService.rateMovie(ratingRequest, userId),
                HttpStatus.CREATED
        );


    }
}