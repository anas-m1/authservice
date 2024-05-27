package com.example.authservice.security;

import com.example.authservice.models.Role;
import com.example.authservice.models.User;
import org.springframework.security.core.GrantedAuthority;

public class CustomGrantedAuthority implements GrantedAuthority {
    Role role;

    CustomGrantedAuthority(Role role){
        this.role=role;
    }

    @Override
    public String getAuthority() {
        return this.role.getRole();
    }
}
