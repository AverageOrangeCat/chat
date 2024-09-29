package org.chat.backend.repositories;

import java.util.Optional;

import org.chat.backend.services.credentials.CredentialsCreateView;
import org.chat.backend.services.credentials.CredentialsView;
import org.chat.backend.services.credentials.PasswordView;
import org.chat.backend.services.current_user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CredentialsRepository {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<PasswordView> getPasswordView(String usertag) {
        var passwordViews = namedParameterJdbcTemplate.query(
                """
                        SELECT password_salt, password_hash FROM credentials
                        WHERE usertag = :usertag
                        """,
                new MapSqlParameterSource("usertag", usertag),
                (resultSet, rowNumber) -> new PasswordView()
                        .setPasswordSalt(resultSet.getString("password_salt"))
                        .setPasswordHash(resultSet.getString("password_hash")));

        return passwordViews.isEmpty()
                ? Optional.empty()
                : Optional.of(passwordViews.get(0));
    }

    public Optional<CredentialsView> getCredentialsView(String usertag) {
        var credentialViews = namedParameterJdbcTemplate.query(
                "SELECT * FROM credentials WHERE usertag = :usertag",
                new MapSqlParameterSource("usertag", usertag),
                (resultSet, rowNumber) -> new CredentialsView()
                        .setId(resultSet.getLong("credential_id"))
                        .setUsertag(resultSet.getString("usertag"))
                        .setUsername(resultSet.getString("username"))
                        .setPasswordSalt(resultSet.getString("password_salt"))
                        .setPasswordHash(resultSet.getString("password_hash")));

        return credentialViews.isEmpty()
                ? Optional.empty()
                : Optional.of(credentialViews.get(0));
    }

    public Integer create(CredentialsCreateView credentialsCreateView) {
        return namedParameterJdbcTemplate.update(
                """
                        INSERT INTO credentials (usertag, username, password_salt, password_hash)
                        VALUES (:usertag, :username, :password_salt, :password_hash)
                        """,
                new MapSqlParameterSource()
                        .addValue("usertag", credentialsCreateView.getUsertag())
                        .addValue("username", credentialsCreateView.getUsername())
                        .addValue("password_salt", credentialsCreateView.getPasswordSalt())
                        .addValue("password_hash", credentialsCreateView.getPasswordHash()));
    }

    public Integer updateUsertag(String usertag) {
        return namedParameterJdbcTemplate.update(
                """
                        UPDATE credentials SET usertag = :usertag
                        WHERE credential_id = :credential_id
                        """,
                new MapSqlParameterSource()
                        .addValue("credential_id", currentUser
                                .getCredentialsView()
                                .getId())
                        .addValue("usertag", usertag));
    }

    public Integer updateUsername(String username) {
        return namedParameterJdbcTemplate.update(
                """
                        UPDATE credentials SET username = :username
                        WHERE credential_id = :credential_id
                        """,
                new MapSqlParameterSource()
                        .addValue("credential_id", currentUser
                                .getCredentialsView()
                                .getId())
                        .addValue("username", username));
    }

    public Integer updatePasswordSalt(String passwordSalt) {
        return namedParameterJdbcTemplate.update(
                """
                        UPDATE credentials SET password_salt = :password_salt
                        WHERE credential_id = :credential_id
                        """,
                new MapSqlParameterSource()
                        .addValue("credential_id", currentUser
                                .getCredentialsView()
                                .getId())
                        .addValue("password_salt", passwordSalt));
    }

    public Integer updatePasswordHash(String passwordHash) {
        return namedParameterJdbcTemplate.update(
                """
                        UPDATE credentials SET password_hash = :password_hash
                        WHERE credential_id = :credential_id
                        """,
                new MapSqlParameterSource()
                        .addValue("credential_id", currentUser
                                .getCredentialsView()
                                .getId())
                        .addValue("password_hash", passwordHash));
    }

    public Integer delete() {
        return namedParameterJdbcTemplate.update(
                "DELETE FROM credentials WHERE credential_id = :credential_id",
                new MapSqlParameterSource("credential_id", currentUser
                        .getCredentialsView()
                        .getId()));
    }

}
