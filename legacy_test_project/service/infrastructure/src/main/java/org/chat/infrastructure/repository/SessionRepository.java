package org.chat.infrastructure.repository;

import java.time.LocalDateTime;

import org.chat.infrastructure.entity.SessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SessionRepository extends JpaRepository<SessionEntity, Long> {
    
    @Query(value = "SELECT * FROM sessions WHERE access_token = :access_token", nativeQuery = true)
    SessionEntity findSessionEntityByAcessToken(@Param("access_token") String accessToken);

    @Modifying
    @Transactional
    @Query(
        value = """
            INSERT INTO sessions (account_id, access_token, expiration_date)
            VALUES (:account_id, :access_token, :expiration_date);
            """,
        nativeQuery = true
    )
    void createSessionEntity(
        @Param("account_id") Long account_id,
        @Param("access_token") String accessToken, 
        @Param("expiration_date") LocalDateTime expirationDate
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM sessions WHERE access_token = :access_token", nativeQuery = true)
    void deleteSessionEntityy(@Param("access_token") String accessToken);
}
