package com.example.authservice.models;

import com.example.authservice.dtos.UserDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name="users")
public class User extends BaseClass  {
    public String email;
    String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role>roles=new HashSet<>();

    public static User from(UserDTO userDTO){
        User user=new User();
        user.email=userDTO.email;
        user.password=userDTO.password;
        return user;
    }
}
