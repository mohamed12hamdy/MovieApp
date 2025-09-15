package com.fawry.movieapp.mapper;

import com.fawry.movieapp.model.dto.request.RegistrationRequest;
import com.fawry.movieapp.model.entity.User;
import com.fawry.movieapp.role.Role;
import com.fawry.movieapp.util.UserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private  final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User toEntity(RegistrationRequest request){
        User newUser = new User();
        newUser.setUsername(request.getName());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setEmail(request.getEmail());
        newUser.setRole(Role.USER);
        return newUser;
    }
}
