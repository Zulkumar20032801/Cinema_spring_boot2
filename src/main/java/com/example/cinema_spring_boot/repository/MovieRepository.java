package com.example.cinema_spring_boot.repository;

//import com.example.cinema_spring_boot.model.Movie;
import com.example.cinema_spring_boot.model.Movie2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie2,Long> {
    Movie2 findByName(String name);
}
