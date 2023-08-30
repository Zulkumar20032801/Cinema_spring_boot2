package com.example.cinema_spring_boot.repository;

import com.example.cinema_spring_boot.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session,Long > {
    Session findByName(String name);
    @Query("from Session s where s.movie2.id = ?1")
    List<Session> findAllByMovieId(Long id);
}
