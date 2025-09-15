package com.fawry.movieapp.model.entity;

import com.fawry.movieapp.role.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@ToString(exclude = "rates")
@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name="user_name",nullable=false)
    private String username;

    @Column(name="user_email",nullable=false)
    private String email;

    @Column(name="user_password",nullable=false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Rate> rates;

}
