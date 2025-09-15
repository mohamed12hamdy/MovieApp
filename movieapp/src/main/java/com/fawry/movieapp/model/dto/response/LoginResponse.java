package com.fawry.movieapp.model.dto.response;

import com.fawry.movieapp.role.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private Integer id;
    private String email;
    private String token;
    private Role role;

}
