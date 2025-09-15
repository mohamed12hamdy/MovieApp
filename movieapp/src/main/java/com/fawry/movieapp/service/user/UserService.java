package com.fawry.movieapp.service.user;

import com.fawry.movieapp.model.dto.request.LoginRequest;
import com.fawry.movieapp.model.dto.request.RegistrationRequest;
import com.fawry.movieapp.model.dto.response.LoginResponse;
import com.fawry.movieapp.model.dto.response.RegistrationResponse;
import com.fawry.movieapp.model.entity.User;
import jakarta.validation.Valid;

public interface UserService {


    RegistrationResponse register(RegistrationRequest request);


    LoginResponse login(@Valid LoginRequest request);
}
