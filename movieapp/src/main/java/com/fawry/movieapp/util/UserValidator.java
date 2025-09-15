package com.fawry.movieapp.util;
import com.fawry.movieapp.exception.user.UserAlreadyExistException;
import com.fawry.movieapp.model.dto.request.LoginRequest;
import com.fawry.movieapp.model.dto.request.RegistrationRequest;
import com.fawry.movieapp.model.entity.User;
import com.fawry.movieapp.repository.user.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public  class UserValidator {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;

    public UserValidator(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public void validateNewUser(RegistrationRequest request) {
        if (userRepo.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistException("User with email " + request.getEmail() +
                    " is already registered");
        }
    }

    public boolean checkCredentials(LoginRequest request, User user) {
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }
        return true;
    }


}
