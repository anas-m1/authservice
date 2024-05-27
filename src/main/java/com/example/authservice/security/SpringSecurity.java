package com.example.authservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.SecretKey;
import java.util.HashMap;

@Configuration
public class SpringSecurity {

    @Bean
    public SecurityFilterChain filteringCriteria(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors().disable();
        httpSecurity.csrf().disable();
        httpSecurity.authorizeHttpRequests
                (authorize->authorize.anyRequest().permitAll());
        return httpSecurity.build();
    }

    @Bean
    public BCryptPasswordEncoder getBcryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HashMap<MacAlgorithm, SecretKey> getAlgAndKey(){
        MacAlgorithm alg= Jwts.SIG.HS256;
        SecretKey key=alg.key().build();

        HashMap<MacAlgorithm,SecretKey> hm=new HashMap();
        hm.put(alg,key);
        return hm;
    }

}
