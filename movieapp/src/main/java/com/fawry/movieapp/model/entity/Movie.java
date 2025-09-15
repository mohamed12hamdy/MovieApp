package com.fawry.movieapp.model.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
@Table(name = "movies")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private int id;

    @Column(nullable = false)
    private String title;

    private String genre;

    private String director;

    private String year;

    @Column(nullable = false)
    private String poster;

    @OneToMany(mappedBy = "movie")
    @ToString.Exclude
    private List<Rate> rates;

}
