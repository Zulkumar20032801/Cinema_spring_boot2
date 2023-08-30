package com.example.cinema_spring_boot.repository;

import com.example.cinema_spring_boot.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    Room findByName(String name );

    List<Room> findAllByById(Long id);
}
