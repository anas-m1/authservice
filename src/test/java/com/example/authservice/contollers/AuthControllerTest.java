package com.example.authservice.contollers;

import com.example.authservice.dtos.UserDTO;
import com.example.authservice.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthControllerTest {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserController userController;
    @Test
    void signup() {
        UserDTO userDTO=new UserDTO();
        userDTO.setEmail("abc");
        userDTO.setPassword("abc");
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User user1=userController.createUser(userDTO);
    }
}