package com.example.cinema_spring_boot.layer.impl;

import com.example.cinema_spring_boot.exception.EntityNotFoundException;
import com.example.cinema_spring_boot.layer.Layer;
import com.example.cinema_spring_boot.model.Place;
import com.example.cinema_spring_boot.model.Room;
import com.example.cinema_spring_boot.repository.RoomRepository;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RoomLayer implements Layer<Room> {
     RoomRepository roomRepository;

    @Override
    public  void save(Room room) {
        roomRepository.save(room);

    }

    @Override
    public Room findById(Long id) {
        return roomRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(String.format("Place with id=%d not found", id)));
    }
    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room update(Long id, Room room) {
        Room oldRoom=findById(id);
        oldRoom.setName(room.getName());
        oldRoom.setRating(room.getRating());
        roomRepository.save(oldRoom);
        return oldRoom;
    }

    @Override
    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room findByName(String name) {
        return roomRepository.findByName(name);
    }

    public List<Room> findAllId(Long id) {
        return roomRepository.findAllById(Collections.singleton(id));
    }
}