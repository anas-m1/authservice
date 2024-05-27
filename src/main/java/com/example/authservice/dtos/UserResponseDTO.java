package com.example.authservice.dtos;
import com.example.authservice.models.User;
import lombok.Data;

//this class is created because the response of controller cant be userdto as it contains password
//userdto is input to controller from client
@Data
public class UserResponseDTO {
    String email;

    public static UserResponseDTO from(User user){
        UserResponseDTO dto=new UserResponseDTO();
        dto.email=user.email;
        return dto;
    }
}
