package com.example.authservice.models;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Role extends BaseClass {
    private String role;
}
