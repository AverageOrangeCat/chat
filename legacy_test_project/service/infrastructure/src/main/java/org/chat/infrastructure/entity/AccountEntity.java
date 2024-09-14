package org.chat.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long id = 0l;

    @Column(name = "usertag", nullable = false)
    private String usertag = "";

    @Column(name = "username", nullable = false)
    private String username = "";

    @Column(name = "password", nullable = false)
    private String password = "";

    public AccountEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AccountEntity setUsertag(String usertag) {
        this.usertag = usertag;
        return this;
    }

    public String getUsertag() {
        return usertag;
    }

    public AccountEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AccountEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPassword() {
        return password;
    }

}
