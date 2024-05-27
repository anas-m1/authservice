package com.example.authservice.dtos;

import com.example.authservice.models.Session;
import com.example.authservice.models.User;

import java.util.Date;

public class SessionDTO {
    private Date expiryAt;
    User user;

    public static SessionDTO from(Session savedSession) {
        SessionDTO sessionDTO=new SessionDTO();
        sessionDTO.user=savedSession.user;
        sessionDTO.expiryAt=savedSession.expiryAt;

        return sessionDTO;
    }
}
