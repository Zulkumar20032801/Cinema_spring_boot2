package com.example.cinema_spring_boot.model;

import com.example.cinema_spring_boot.enums.Genre;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "test")
public class Movie2 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    Genre genre;
    LocalDate localDate;
    String country;
    String language;
    byte[] image;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY,mappedBy = "movie")

    List<Session> sessions;
}
