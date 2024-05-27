package com.example.authservice.contollers;

import com.example.authservice.dtos.SessionDTO;
import com.example.authservice.models.Session;
import com.example.authservice.models.User;
import com.example.authservice.repositories.SessionRepository;

public class SessionController {
    SessionRepository sessionRepository;

    SessionController(SessionRepository sessionRepository){
        this.sessionRepository=sessionRepository;
    }

    SessionDTO createSession(User user){
        Session session=new Session();
        session.user=user;

        Session savedSession= sessionRepository.save(session);

        return SessionDTO.from(savedSession);
    }

    SessionDTO closeSession(Session session){
        sessionRepository.close(session.getId());
        return SessionDTO.from(session);
    }
}