package com.fawry.movieapp.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@Builder
@Entity
@Table(name = "user_movie_rate", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "movie_id"})
})

@EntityListeners(AuditingEntityListener.class)
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id",nullable = false)
    private Movie movie;

    private int rate;

    @CreatedDate
    private LocalDateTime createdAt;
}
