package com.example.cinema_spring_boot.layer.impl;

import com.example.cinema_spring_boot.layer.Layer;
import com.example.cinema_spring_boot.model.Role;
import com.example.cinema_spring_boot.model.User;
import com.example.cinema_spring_boot.repository.RoleRepository;
import com.example.cinema_spring_boot.repository.UserRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLayer implements Layer<User> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender emailSender;
    final RoleRepository roleRepository;

    final PasswordEncoder passwordEncoder;

    public UserLayer(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JavaMailSender emailSender) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailSender = emailSender;
    }


    @Override
    public void save(User user) {
        Role role = null;
        if (user.getName().equals("admin")) {
            role = roleRepository.findByName("ADMIN");
            if (role == null) {
                role = new Role("ADMIN");
            }
        } else {
            role = roleRepository.findByName("USER");
            if (role == null) {
                role = new Role("USER");
            }
        }
//        List<Role> roles = new ArrayList<>();
//        Role role = new Role();
//        if (user.getName().equals("admin")) {
//            role.setName("ADMIN");
//        } else {
//            role.setName("USER");
//        }
        user.setRoles(new ArrayList<>(Collections.singletonList(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(Long id, User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public User findByName(String name) {
        return null;
    }

    public void sendMessage(String email) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setFrom("gadgetarium_application@gmail.com");
        mailMessage.setSubject("Order information!");
        String massage = "This message from my Spring Boot application";
        mailMessage.setText(massage);
        emailSender.send((mailMessage));
    }
}