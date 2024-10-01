package org.chat.backend.repositories;

import java.util.Optional;

import org.chat.backend.exceptions.DatabaseException;
import org.chat.backend.exceptions.credentials.CredentialsAlreadyExistsException;
import org.chat.backend.services.credentials.CredentialsCreateView;
import org.chat.backend.services.credentials.CredentialsView;
import org.chat.backend.services.credentials.PasswordView;
import org.chat.backend.services.current_user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CredentialsRepository {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<PasswordView> getPasswordView(String usertag) throws DatabaseException {
        var query = """
                SELECT password_salt, password_hash FROM credentials
                WHERE usertag = :usertag
                        """;

        var parameters = new MapSqlParameterSource("usertag", usertag);

        try {
            var passwordViews = namedParameterJdbcTemplate.query(query, parameters,
                    (resultSet, rowNumber) -> {
                        return new PasswordView()
                                .setPasswordSalt(resultSet.getString("password_salt"))
                                .setPasswordHash(resultSet.getString("password_hash"));
                    });

            return passwordViews.isEmpty()
                    ? Optional.empty()
                    : Optional.of(passwordViews.get(0));

        } catch (DataAccessException dataAccessException) {
            throw new DatabaseException();
        }
    }

    public Optional<CredentialsView> getCredentialsView(String usertag) throws DatabaseException {
        var query = "SELECT * FROM credentials WHERE usertag = :usertag";
        var parameters = new MapSqlParameterSource("usertag", usertag);

        try {
            var credentialViews = namedParameterJdbcTemplate.query(query, parameters,
                    (resultSet, rowNumber) -> {
                        return new CredentialsView()
                                .setId(resultSet.getLong("credential_id"))
                                .setUsertag(resultSet.getString("usertag"))
                                .setUsername(resultSet.getString("username"))
                                .setPasswordSalt(resultSet.getString("password_salt"))
                                .setPasswordHash(resultSet.getString("password_hash"));
                    });

            return credentialViews.isEmpty()
                    ? Optional.empty()
                    : Optional.of(credentialViews.get(0));

        } catch (DataAccessException dataAccessException) {
            throw new DatabaseException();
        }
    }

    public Integer createCredentials(CredentialsCreateView credentialsCreateView)
            throws DatabaseException, CredentialsAlreadyExistsException {

        var query = """
                INSERT INTO credentials (usertag, username, password_salt, password_hash)
                VALUES (:usertag, :username, :password_salt, :password_hash)
                """;

        var parameters = new MapSqlParameterSource()
                .addValue("usertag", credentialsCreateView.getUsertag())
                .addValue("username", credentialsCreateView.getUsername())
                .addValue("password_salt", credentialsCreateView.getPasswordSalt())
                .addValue("password_hash", credentialsCreateView.getPasswordHash());

        try {
            return namedParameterJdbcTemplate.update(query, parameters);

        } catch (DataAccessException dataAccessException) {

            if (dataAccessException instanceof DataIntegrityViolationException) {
                throw new CredentialsAlreadyExistsException(credentialsCreateView.getUsertag());
            }

            throw new DatabaseException();
        }
    }

    public Integer updateCredentialUsertag(String usertag) throws DatabaseException {
        var query = """
                UPDATE credentials SET usertag = :usertag
                WHERE credential_id = :credential_id
                """;

        var credentialId = currentUser
                .getCredentialsView()
                .getId();

        var parameters = new MapSqlParameterSource()
                .addValue("credential_id", credentialId)
                .addValue("usertag", usertag);

        try {
            return namedParameterJdbcTemplate.update(query, parameters);

        } catch (DataAccessException dataAccessException) {
            throw new DatabaseException();
        }
    }

    public Integer updateCredentialUsername(String username) throws DatabaseException {
        var query = """
                UPDATE credentials SET username = :username
                WHERE credential_id = :credential_id
                """;

        var credentialId = currentUser
                .getCredentialsView()
                .getId();

        var parameters = new MapSqlParameterSource()
                .addValue("credential_id", credentialId)
                .addValue("username", username);

        try {
            return namedParameterJdbcTemplate.update(query, parameters);

        } catch (DataAccessException dataAccessException) {
            throw new DatabaseException();
        }
    }

    public Integer updateCredentialPasswordSalt(String passwordSalt) throws DatabaseException {
        var query = """
                UPDATE credentials SET password_salt = :password_salt
                WHERE credential_id = :credential_id
                """;

        var credentialId = currentUser
                .getCredentialsView()
                .getId();

        var parameters = new MapSqlParameterSource()
                .addValue("credential_id", credentialId)
                .addValue("password_salt", passwordSalt);

        try {
            return namedParameterJdbcTemplate.update(query, parameters);

        } catch (DataAccessException dataAccessException) {
            throw new DatabaseException();
        }
    }

    public Integer updateCredentialPasswordHash(String passwordHash) throws DatabaseException {
        var query = """
                UPDATE credentials SET password_hash = :password_hash
                WHERE credential_id = :credential_id
                """;

        var credentialId = currentUser
                .getCredentialsView()
                .getId();

        var parameters = new MapSqlParameterSource()
                .addValue("credential_id", credentialId)
                .addValue("password_hash", passwordHash);

        try {
            return namedParameterJdbcTemplate.update(query, parameters);

        } catch (DataAccessException dataAccessException) {
            throw new DatabaseException();
        }
    }

    public Integer deleteCredentials() throws DataAccessException, DatabaseException {
        var query = "DELETE FROM credentials WHERE credential_id = :credential_id";
        var credentialId = currentUser
                .getCredentialsView()
                .getId();

        var parameters = new MapSqlParameterSource("credential_id", credentialId);

        try {
            return namedParameterJdbcTemplate.update(query, parameters);

        } catch (DataAccessException dataAccessException) {
            throw new DatabaseException();
        }
    }

}
