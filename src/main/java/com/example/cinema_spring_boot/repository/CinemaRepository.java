package com.example.cinema_spring_boot.repository;

import com.example.cinema_spring_boot.model.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema,Long> {
    Cinema findByName(String name);
}
