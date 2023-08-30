package com.example.cinema_spring_boot.repository;

import com.example.cinema_spring_boot.model.Session;
import com.example.cinema_spring_boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
    User findByName(String name);
}
