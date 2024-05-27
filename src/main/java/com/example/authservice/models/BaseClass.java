package com.example.authservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@MappedSuperclass
@Data
public class BaseClass {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Date createdAt;
}
