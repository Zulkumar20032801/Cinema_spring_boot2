package com.example.cinema_spring_boot.layer.impl;

import com.example.cinema_spring_boot.exception.EntityNotFoundException;
import com.example.cinema_spring_boot.layer.Layer;
import com.example.cinema_spring_boot.model.Session;
import com.example.cinema_spring_boot.repository.SessionRepository;
import jakarta.persistence.PersistenceContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SessionLayer implements Layer<Session> {
     SessionRepository sessionRepository;

    @Override
    public void save(Session session) {
        sessionRepository.save(session);

    }

    @Override
    public Session findById(Long id) {
        return sessionRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException(String.format("Session with id=%d not found", id)));
    }
    @Override
    public List<Session> findAll() {
        return  sessionRepository.findAll();
    }

    @Override
    public Session update(Long id, Session session) {
        Session oldSession = findById( id);

        oldSession.setName(session.getName());
        oldSession.setStart(session.getStart());
        oldSession.setFinish(session.getFinish());

        sessionRepository.save(oldSession);
        return oldSession;
    }

    @Override
    public void deleteById(Long id) {
        sessionRepository.deleteById(id);
    }

    @Override
    public Session findByName(String name) {
        return sessionRepository.findByName(name);
    }
}
