package com.fawry.movieapp.controller.auth;

import com.fawry.movieapp.model.dto.request.LoginRequest;
import com.fawry.movieapp.model.dto.request.RegistrationRequest;
import com.fawry.movieapp.model.dto.response.LoginResponse;
import com.fawry.movieapp.model.dto.response.RegistrationResponse;
import com.fawry.movieapp.service.jwt.JwtService;
import com.fawry.movieapp.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    private final JwtService jwtService;
    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register
            (@Valid @RequestBody RegistrationRequest request) {
        return new ResponseEntity<>(userService.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return new ResponseEntity<>(userService.login(request),HttpStatus.OK);

    }



}
