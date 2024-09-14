package org.chat.infrastructure.repository;

import org.chat.infrastructure.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query(value = "SELECT * FROM accounts WHERE usertag = :usertag", nativeQuery = true)
    AccountEntity findAccountEntityByUsertag(@Param("usertag") String usertag);

    @Query(value = "SELECT * FROM accounts WHERE usertag = :usertag AND password = :password", nativeQuery = true)
    AccountEntity findAccountEntityByUsertagAndPassword(
        @Param("usertag") String usertag,
        @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE accounts SET usertag = :usertag WHERE account_id = :account_id", nativeQuery = true)
    void updateAccountEntityUsertag(
        @Param("account_id") Long id,
        @Param("usertag") String usertag);

    @Modifying
    @Transactional
    @Query(value = "UPDATE accounts SET username = :username WHERE account_id = :account_id", nativeQuery = true)
    void updateAccountEntityUsername(
        @Param("account_id") Long id,
        @Param("username") String username);

    @Modifying
    @Transactional
    @Query(value = "UPDATE accounts SET password = :password WHERE account_id = :account_id", nativeQuery = true)
    void updateAccountEntityPassword(
        @Param("account_id") Long id,
        @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO accounts (usertag, username, password)
            VALUES (:usertag, :username, :password);
            """, nativeQuery = true)
    void createAccountEntity(
        @Param("usertag") String usertag,
        @Param("username") String username,
        @Param("password") String password);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM accounts WHERE account_id = :account_id", nativeQuery = true)
    void deleteAccountEntity(@Param("account_id") Long id);
}
