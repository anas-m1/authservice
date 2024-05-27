package com.example.authservice.dtos;

import com.example.authservice.models.User;
import lombok.Data;

@Data
public class UserDTO {
    public String email;
    public String password;

    public static UserDTO from(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.email=user.email;
        return userDTO;
    }
}
