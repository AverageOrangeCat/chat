package org.chat.backend.repositories;

import java.util.Optional;

import org.chat.backend.services.session.SessionLoginView;
import org.chat.backend.services.session.SessionView;
import org.chat.backend.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SessionRepository {

    @Autowired
    private UserService userService;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<SessionView> getSessionView(String bearToken) {
        var sessionViews = namedParameterJdbcTemplate.query(
                "SELECT * FROM sessions WHERE bear_token = :bear_token",
                new MapSqlParameterSource("bear_token", bearToken),
                (resultSet, rowNumber) -> new SessionView()
                        .setId(resultSet.getLong("session_id"))
                        .setBearToken(resultSet.getString("bear_token"))
                        .setUsertag(resultSet.getString("usertag")));

        return sessionViews.isEmpty()
                ? Optional.empty()
                : Optional.of(sessionViews.get(0));
    }

    public Optional<SessionView> login(SessionLoginView sessionLoginView) {
        var sessionViews = namedParameterJdbcTemplate.query(
                """
                        INSERT INTO sessions (bear_token, usertag)
                        VALUES (:bear_token, :usertag)
                        RETURNING *
                        """,
                new MapSqlParameterSource()
                        .addValue("bear_token", sessionLoginView.getBearToken())
                        .addValue("usertag", sessionLoginView.getUsertag()),
                (resultSet, rowNumber) -> new SessionView()
                        .setId(resultSet.getLong("session_id"))
                        .setBearToken(resultSet.getString("bear_token"))
                        .setUsertag(resultSet.getString("usertag")));

        return sessionViews.isEmpty()
                ? Optional.empty()
                : Optional.of(sessionViews.get(0));
    }

    public Integer logout() {
        var user = userService.getUser();
        return namedParameterJdbcTemplate.update(
                "DELETE FROM sessions WHERE session_id = :session_id",
                new MapSqlParameterSource("session_id", user
                        .getSessionView()
                        .getId()));
    }

}
