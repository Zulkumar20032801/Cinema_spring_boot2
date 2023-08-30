package com.example.cinema_spring_boot.layer.impl;

import com.example.cinema_spring_boot.exception.EntityNotFoundException;
import com.example.cinema_spring_boot.layer.Layer;
import com.example.cinema_spring_boot.model.Movie2;
import com.example.cinema_spring_boot.repository.MovieRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MovieLayer implements Layer<Movie2> {

    MovieRepository movieRepository;

    @Override
    public void save(Movie2 movie2) {
        movieRepository.save(movie2);
    }

    @Override
    public Movie2 findById(Long id) {
        return movieRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(String.format("Movie with id=%d not found", id)));
    }

    @Override
    public List<Movie2> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie2 update(Long id, Movie2 movie2) {
        Movie2 oldMovie = findById(id);
        oldMovie.setName(movie2.getName());
        oldMovie.setCountry(movie2.getCountry());
        oldMovie.setLocalDate(movie2.getLocalDate());
        oldMovie.setLanguage(movie2.getLanguage());
        oldMovie.setGenre(movie2.getGenre());
        oldMovie.setImage(movie2.getImage());
        movieRepository.save(oldMovie);
        return oldMovie;
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie2 findByName(String name) {
        return movieRepository.findByName(name);
    }



}

