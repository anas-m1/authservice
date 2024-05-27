package com.example.authservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Session extends BaseClass{
    @ManyToOne
    public User user;
    public String token;
    public Date expiryAt;
}
