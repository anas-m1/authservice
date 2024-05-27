package com.example.authservice.security;

import com.example.authservice.models.User;
import com.example.authservice.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomSpringUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    CustomSpringUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepository.findByEmail(email);
        if(Objects.isNull(user)){
            throw new UsernameNotFoundException("user not found");
        }


        return new CustomUserDetails(user);
    }
}
