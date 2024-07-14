package com.example.authservice.repositories;

import com.example.authservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
    public Session save(Session session);

    Session findByUserIdAndToken(Long userId,String token);

    @Query(value="DELETE FROM users WHERE id = ?1",nativeQuery = true)
    void close(Long sessionId);
}
