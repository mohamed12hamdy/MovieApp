package com.fawry.movieapp.model.dto.request;

import com.fawry.movieapp.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrationRequest {

    @NotBlank(message = "name can not be null or empty")
    private String name;

    @Email
    @NotBlank(message = "Email can not be null or empty")
    private String email;

    @NotBlank(message = "Password can not be null or empty")
    private String password;

}
