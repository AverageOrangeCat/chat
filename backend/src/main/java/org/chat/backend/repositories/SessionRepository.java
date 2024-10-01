package org.chat.backend.repositories;

import java.util.Optional;

import org.chat.backend.exceptions.DatabaseException;
import org.chat.backend.services.current_user.CurrentUser;
import org.chat.backend.services.session.SessionLoginView;
import org.chat.backend.services.session.SessionView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SessionRepository {

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<SessionView> getSessionView(String bearToken) throws DatabaseException {
        var query = "SELECT * FROM sessions WHERE bear_token = :bear_token";
        var parameters = new MapSqlParameterSource("bear_token", bearToken);

        try {
            var sessionViews = namedParameterJdbcTemplate.query(query, parameters,
                    (resultSet, rowNumber) -> {
                        return new SessionView()
                                .setId(resultSet.getLong("session_id"))
                                .setBearToken(resultSet.getString("bear_token"))
                                .setUsertag(resultSet.getString("usertag"));
                    });

            return sessionViews.isEmpty()
                    ? Optional.empty()
                    : Optional.of(sessionViews.get(0));

        } catch (DataAccessException dataAccessException) {
            throw new DatabaseException();
        }
    }

    public Optional<SessionView> login(SessionLoginView sessionLoginView) throws DatabaseException {
        var query = """
                INSERT INTO sessions (bear_token, usertag)
                VALUES (:bear_token, :usertag)
                RETURNING *
                """;

        var parameters = new MapSqlParameterSource()
                .addValue("bear_token", sessionLoginView.getBearToken())
                .addValue("usertag", sessionLoginView.getUsertag());

        try {
            var sessionViews = namedParameterJdbcTemplate.query(query, parameters,
                    (resultSet, rowNumber) -> {
                        return new SessionView()
                                .setId(resultSet.getLong("session_id"))
                                .setBearToken(resultSet.getString("bear_token"))
                                .setUsertag(resultSet.getString("usertag"));
                    });

            return sessionViews.isEmpty()
                    ? Optional.empty()
                    : Optional.of(sessionViews.get(0));

        } catch (DataAccessException dataAccessException) {
            throw new DatabaseException();
        }
    }

    public Integer logout() throws DatabaseException {
        var query = "DELETE FROM sessions WHERE session_id = :session_id";
        var sessionId = currentUser
                .getSessionView()
                .getId();

        var parameters = new MapSqlParameterSource("session_id", sessionId);

        try {
            return namedParameterJdbcTemplate.update(query, parameters);

        } catch (DataAccessException dataAccessException) {
            throw new DatabaseException();
        }
    }

}
