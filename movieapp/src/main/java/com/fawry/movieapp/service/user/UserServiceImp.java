package com.fawry.movieapp.service.user;

import com.fawry.movieapp.exception.user.UserNotFoundException;
import com.fawry.movieapp.mapper.UserMapper;
import com.fawry.movieapp.model.dto.request.LoginRequest;
import com.fawry.movieapp.model.dto.request.RegistrationRequest;
import com.fawry.movieapp.model.dto.response.LoginResponse;
import com.fawry.movieapp.model.dto.response.RegistrationResponse;
import com.fawry.movieapp.model.entity.User;
import com.fawry.movieapp.repository.user.UserRepository;
import com.fawry.movieapp.role.Role;
import com.fawry.movieapp.service.jwt.JwtService;
import com.fawry.movieapp.util.UserValidator;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepo;

    private final UserValidator userValidator;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    public UserServiceImp(UserRepository userRepo,
                          UserValidator userValidator,UserMapper userMapper, JwtService jwtService) {
        this.userRepo = userRepo;

        this.userValidator = userValidator;
        this.userMapper = userMapper;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public RegistrationResponse register(RegistrationRequest request) {
        userValidator.validateNewUser(request);
        User user = userMapper.toEntity(request);
        userRepo.save(user);
        return RegistrationResponse.builder()
                .message("user registered successfully").build();
    }

    @Override
    @Transactional
    public LoginResponse login(LoginRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException
                        ("User not found with email: " + request.getEmail()));

         userValidator.checkCredentials(request,user);
         return LoginResponse.builder().id(user.getId())
                 .email(user.getEmail())
                 .token(jwtService.generateToken(user))
                 .role(user.getRole())
                 .build();
    }
}
