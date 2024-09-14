package org.chat.infrastructure.entity;

import java.time.LocalDateTime;

import org.chat.shared.time.TimeService;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sessions")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "session_id", nullable = false)
    private Long id = 0l;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity accountEntity = new AccountEntity();

    @Column(name = "access_token", nullable = false)
    private String accessToken = "";

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate = TimeService.nowUtc();

    public SessionEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public SessionEntity setAccouEntity(AccountEntity accountEntity) {
        this.accountEntity = accountEntity;
        return this;
    }

    public AccountEntity getAccountEntity() {
        return accountEntity;
    }

    public SessionEntity setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public SessionEntity setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

}
