package com.example.authservice.contollers;

import com.example.authservice.dtos.UserDTO;
import com.example.authservice.dtos.UserResponseDTO;
import com.example.authservice.models.User;
import com.example.authservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class UserController {
    UserRepository userRepository;

//    @Autowired
    UserController(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    User createUser(UserDTO userDTO){
        User user=User.from(userDTO);
        User savedUser=userRepository.save(user);

        return savedUser;
    }


    User findUser(UserDTO userDTO){
        User user= userRepository.findByEmailAndPassword(userDTO.email, userDTO.password);
        if(Objects.isNull(user))return null;
        return user;
    }

    User findUserByEmail(UserDTO userDTO){
        System.out.println(userDTO.getEmail());
        User user=userRepository.findByEmail(userDTO.email);

        if(Objects.isNull(user)) {
            System.out.println("user not found");
            return null;
        }
        return user;
    }

    User deleteUser(User user){
        userRepository.delete(user);
        return user;
    }
}
