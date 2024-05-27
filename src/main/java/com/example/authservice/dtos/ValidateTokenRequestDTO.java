package com.example.authservice.dtos;

import lombok.Data;

@Data
public class ValidateTokenRequestDTO {
    String token;
    Long userId;
}
