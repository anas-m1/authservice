package com.example.authservice.repositories;

import com.example.authservice.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User save(User user);
    public void delete(User user);

//    @Query(value = "SELECT * FROM users WHERE id = :userId", nativeQuery = true)

    @Query(value="select  from users where id=?1 and password=?2",nativeQuery = true)
    User findByEmailAndPassword(String user,String password);

    User findByEmail(String email);
}
