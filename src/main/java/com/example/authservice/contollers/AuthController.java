package com.example.authservice.contollers;

import com.example.authservice.dtos.UserDTO;
import com.example.authservice.dtos.UserResponseDTO;
import com.example.authservice.dtos.ValidateTokenRequestDTO;
import com.example.authservice.models.Session;
import com.example.authservice.models.User;
import com.example.authservice.repositories.SessionRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
public class AuthController {
    public UserController userController;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private SessionRepository sessionRepository;
    HashMap<MacAlgorithm,SecretKey> hm;


    public AuthController(UserController userController,
                          BCryptPasswordEncoder bcryptPasswordEncoder,
                          SessionRepository sessionRepository,
                          HashMap<MacAlgorithm,SecretKey> hm){
        this.userController=userController;
        this.bCryptPasswordEncoder=bcryptPasswordEncoder;
        this.sessionRepository=sessionRepository;
        this.hm=hm;

    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> signup(@RequestBody UserDTO userDTO){
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User user1=userController.createUser(userDTO);

        UserResponseDTO responseDTO=UserResponseDTO.from(user1);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserDTO userDTO){
        User user=userController.findUserByEmail(userDTO);

        if(!bCryptPasswordEncoder.matches(userDTO.getPassword(),user.getPassword())){
            System.out.println("mou");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        if(Objects.isNull(user)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


        HashMap<String,String> payload=new HashMap<>();
        payload.put("payload","payload");
        byte[] content=payload.toString().getBytes(StandardCharsets.UTF_8);

        MacAlgorithm alg=null;
        SecretKey skey=null;
        for (MacAlgorithm key:hm.keySet()){
            alg=key;
            skey=hm.get(key);
        }

        System.out.println("in login :: "+alg+" : "+ Base64.getEncoder().encodeToString(skey.getEncoded()));


        String token = Jwts.builder().content(content,"text/plain").signWith(skey,alg).compact();

//        save cookie in session
//        Session s=new Session();
//        s.setUser(user);
//        s.setToken(token);
//        sessionRepository.save(s);

        MultiValueMap<String,String> headers=new LinkedMultiValueMap<>();
        headers.add("AUTh-COOKIE",token);

        return new ResponseEntity<>(UserResponseDTO.from(user),headers,HttpStatus.OK);
    }

    @PostMapping("/logout")
    public UserResponseDTO logout(UserDTO userDTO){
        return new UserResponseDTO();
    }


//    ideally validate should be in resource server
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestBody ValidateTokenRequestDTO reqDTO){
        System.out.println("here::"+reqDTO.getUserId()+":"+reqDTO.getToken());

//        MacAlgorithm alg=null;
//        SecretKey key=alg.key().build();

        MacAlgorithm alg= hm.keySet().iterator().next();
        SecretKey skey=hm.values().iterator().next();

        System.out.println("in validate :: "+alg+" : "+Base64.getEncoder().encodeToString(skey.getEncoded()));



        Jwt jwt=
                Jwts.parser().verifyWith(skey).build()
                        .parse(reqDTO.getToken());
        System.out.println(jwt+"hello claims");
//        if(jws.isEmpty()){
//            System.out.println("claims got empty");
//            return null;
//        }
//
////        claims.get();

        return new ResponseEntity<>(true,HttpStatus.OK);
    }


    @PostMapping("/validateSessionToken")
    public ResponseEntity<Boolean> validateSessionToken(@RequestBody ValidateTokenRequestDTO reqDTO){
        System.out.println("here::"+reqDTO.getUserId()+":"+reqDTO.getToken());
        Session s= sessionRepository.findByUserIdAndToken(reqDTO.getUserId(),reqDTO.getToken());

//        System.out.println(s+" : "+s.toString()+" : ");
        if(Objects.isNull(s))
            return new ResponseEntity<>(false,HttpStatus.OK);

        return new ResponseEntity<>(true,HttpStatus.OK);
    }
}
