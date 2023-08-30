package com.example.cinema_spring_boot.layer;

import java.util.List;

public interface Layer <T>{
    void save(T t);

    T findById(Long id);

    List<T> findAll();

    T update(Long id, T t);

    void deleteById(Long id);

    T findByName(String name);


}