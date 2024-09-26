package org.chat.backend.repositories;

import java.util.Optional;

import org.chat.backend.services.credentials.CredentialsCreateView;
import org.chat.backend.services.credentials.CredentialsView;
import org.chat.backend.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CredentialsRepository {

    @Autowired
    private UserService userService;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<byte[]> getPasswordSalt(String usertag) {
        var passwordSalts = namedParameterJdbcTemplate.query(
                """
                        SELECT password_salt FROM credentials
                        WHERE usertag = :usertag
                        """,
                new MapSqlParameterSource("usertag", usertag),
                (resultSet, rowNumber) -> resultSet.getBytes("password_salt"));

        return passwordSalts.isEmpty()
                ? Optional.empty()
                : Optional.of(passwordSalts.get(0));
    }

    public Optional<CredentialsView> getCredentialsView(String usertag) {
        var credentialViews = namedParameterJdbcTemplate.query(
                "SELECT * FROM credentials WHERE usertag = :usertag",
                new MapSqlParameterSource("usertag", usertag),
                (resultSet, rowNumber) -> new CredentialsView()
                        .setId(resultSet.getLong("credential_id"))
                        .setUsertag(resultSet.getString("usertag"))
                        .setUsername(resultSet.getString("username"))
                        .setPasswordSalt(resultSet.getBytes("password_salt"))
                        .setPasswordHash(resultSet.getBytes("password_hash")));

        return credentialViews.isEmpty()
                ? Optional.empty()
                : Optional.of(credentialViews.get(0));
    }

    public Optional<CredentialsView> getCredentialsView(
            String usertag, byte[] passwordSalt, byte[] passwordHash) {
        var credentialViews = namedParameterJdbcTemplate.query(
                "SELECT * FROM credentials WHERE usertag = :usertag",
                new MapSqlParameterSource("usertag", usertag),
                (resultSet, rowNumber) -> new CredentialsView()
                        .setId(resultSet.getLong("credential_id"))
                        .setUsertag(resultSet.getString("usertag"))
                        .setUsername(resultSet.getString("username"))
                        .setPasswordSalt(resultSet.getBytes("password_salt"))
                        .setPasswordHash(resultSet.getBytes("password_hash")));

        return credentialViews.isEmpty()
                ? Optional.empty()
                : Optional.of(credentialViews.get(0));
    }

    public Integer create(CredentialsCreateView credentialsCreateView) {
        return namedParameterJdbcTemplate.update(
                """
                        INSERT INTO credentials (usertag, username, password)
                        VALUES (:usertag, :username, :password)
                        """,
                new MapSqlParameterSource()
                        .addValue("usertag", credentialsCreateView.getUsertag())
                        .addValue("username", credentialsCreateView.getUsername())
                        .addValue("password_salt", credentialsCreateView.getPasswordSalt())
                        .addValue("password_hash", credentialsCreateView.getPasswordHash()));
    }

    public Integer updateUsertag(String usertag) {
        var user = userService.getUser();
        return namedParameterJdbcTemplate.update(
                """
                        UPDATE credentials SET usertag = :usertag
                        WHERE credential_id = :credential_id
                        """,
                new MapSqlParameterSource()
                        .addValue("credential_id", user
                                .getCredentialsView()
                                .getId())
                        .addValue("usertag", usertag));
    }

    public Integer updateUsername(String username) {
        var user = userService.getUser();
        return namedParameterJdbcTemplate.update(
                """
                        UPDATE credentials SET username = :username
                        WHERE credential_id = :credential_id
                        """,
                new MapSqlParameterSource()
                        .addValue("credential_id", user
                                .getCredentialsView()
                                .getId())
                        .addValue("username", username));
    }

    public Integer updatePassword(String password) {
        var user = userService.getUser();
        return namedParameterJdbcTemplate.update(
                """
                        UPDATE credentials SET password = :password
                        WHERE credential_id = :credential_id
                        """,
                new MapSqlParameterSource()
                        .addValue("credential_id", user
                                .getCredentialsView()
                                .getId())
                        .addValue("password", password));
    }

    public Integer delete() {
        var user = userService.getUser();
        return namedParameterJdbcTemplate.update(
                "DELETE FROM credentials WHERE credential_id = :credential_id",
                new MapSqlParameterSource("credential_id", user
                        .getCredentialsView()
                        .getId()));
    }

}
