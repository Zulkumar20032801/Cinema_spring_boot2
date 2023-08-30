package com.example.cinema_spring_boot.layer.impl;
import com.example.cinema_spring_boot.layer.Layer;
import com.example.cinema_spring_boot.model.Cinema;
import com.example.cinema_spring_boot.repository.CinemaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;



import java.util.List;
@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CinemaLayer implements Layer<Cinema> {

   CinemaRepository cinemaRepository;



    @Override
    public void save(Cinema cinema) {
        cinemaRepository.save(cinema);
    }

    @Override
    public Cinema findById(Long id) {
        return cinemaRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(String.format("Cinema with id=%d not found!!!", id)));
    }

    @Override
    public List<Cinema> findAll() {
        return  cinemaRepository.findAll();
    }

    @Override
    public Cinema update(Long id, Cinema cinema) {
        Cinema oldCinema = findById(id);
        oldCinema.setName(cinema.getName());
        oldCinema.setAddress(cinema.getAddress());
        oldCinema.setImage(cinema.getImage());

        cinemaRepository.save(oldCinema);
        return oldCinema;
    }

    @Override
    public void deleteById(Long id) {
        cinemaRepository.deleteById(id);
    }

    @Override
    public Cinema findByName(String name) {
        return cinemaRepository.findByName(name);
    }
}