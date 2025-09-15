package com.fawry.movieapp.exception.movie;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MovieGlobalException {


    @ExceptionHandler(MovieNotFoundException.class)
    public ResponseEntity<Map<String,Object>>handleMovieNotFound(MovieNotFoundException ex) {
             Map<String,Object>body = new HashMap<>();
             body.put("timestamp", LocalDateTime.now());
             body.put("status",404);
             body.put("error","Movie not found");
             body.put("message",ex.getMessage());

             return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
