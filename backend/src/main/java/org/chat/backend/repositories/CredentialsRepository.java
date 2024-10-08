package org.chat.backend.repositories;

import java.util.List;
import java.util.Optional;

import org.chat.backend.services.credentials.CredentialsCreateView;
import org.chat.backend.services.credentials.CredentialsView;
import org.chat.backend.services.user.User;
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

    public Optional<CredentialsView> getView(String usertag) {
        var credentialViews = namedParameterJdbcTemplate.query(
                "SELECT * FROM credentials WHERE usertag = :usertag",
                new MapSqlParameterSource("usertag", usertag),
                (resultSet, rowNumber) -> new CredentialsView(
                        resultSet.getLong("credential_id"),
                        resultSet.getString("usertag"),
                        resultSet.getString("username"),
                        resultSet.getString("password")));

        return credentialViews.isEmpty()
                ? Optional.empty()
                : Optional.of(credentialViews.get(0));
    }

    public Optional<CredentialsView> getView(String usertag, String password) {
        var credentialViews = namedParameterJdbcTemplate.query(
                """
                        SELECT * FROM credentials WHERE usertag = :usertag
                        AND password = :password
                        """,
                new MapSqlParameterSource()
                        .addValue("usertag", usertag)
                        .addValue("password", password),
                (resultSet, rowNumber) -> new CredentialsView(
                        resultSet.getLong("credential_id"),
                        resultSet.getString("usertag"),
                        resultSet.getString("username"),
                        resultSet.getString("password")));

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
                        .addValue("usertag", credentialsCreateView.usertag)
                        .addValue("username", credentialsCreateView.username)
                        .addValue("password", credentialsCreateView.password));
    }

    public Integer updateUsertag(String usertag) {
        var user = userService.getUser();
        return namedParameterJdbcTemplate.update(
                """
                        UPDATE credentials SET usertag = :usertag
                        WHERE credential_id = :credential_id
                        """,
                new MapSqlParameterSource()
                        .addValue("credential_id", user.credentialsView.id)
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
                        .addValue("credential_id", user.credentialsView.id)
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
                        .addValue("credential_id", user.credentialsView.id)
                        .addValue("password", password));
    }

    public Integer delete() {
        var user = userService.getUser();
        return namedParameterJdbcTemplate.update(
                "DELETE FROM credentials WHERE credential_id = :credential_id",
                new MapSqlParameterSource("credential_id", user.credentialsView.id));
    }

}
