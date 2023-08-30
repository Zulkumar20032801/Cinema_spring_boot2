package com.example.cinema_spring_boot.layer.impl;


import com.example.cinema_spring_boot.exception.EntityNotFoundException;
import com.example.cinema_spring_boot.layer.Layer;
import com.example.cinema_spring_boot.model.Place;
import com.example.cinema_spring_boot.repository.PlaceRepository;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PlaceLayer implements Layer<Place> {
   PlaceRepository placeRepository;

    @Override
    public void save(Place place) {
        placeRepository.save(place);

    }

    @Override
    public Place findById(Long id) {
        return placeRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(String.format("Place with id=%d not found", id)));
    }
    @Override
    public List<Place> findAll() {
        return  placeRepository.findAll();
    }

    @Override
    public Place update(Long id, Place place) {
        Place oldPlace =findById(id);
        oldPlace.setX(place.getX());
        oldPlace.setY(place.getY());
        oldPlace.setPrice(place.getPrice());

        placeRepository.save(oldPlace);
        return oldPlace;
    }

    @Override
    public void deleteById(Long id) {
        placeRepository.deleteById(id);
    }

    @Override
    public Place findByName(String name) {
        return null;
    }
}
