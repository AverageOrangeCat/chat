package org.chat.backend.repositories;

import java.util.Optional;

import org.chat.backend.services.session.SessionView;
import org.chat.backend.services.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SessionRepository {

    // Currently logged in user

    @Autowired
    private User user;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Optional<SessionView> getView(String bearToken) {
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT * FROM sessions WHERE bear_token = :bear_token",
                new MapSqlParameterSource("bear_token", bearToken),
                (resultSet, rowNumber) -> Optional.of(
                        new SessionView(
                                resultSet.getLong("session_id"),
                                resultSet.getString("bear_token"),
                                resultSet.getString("usertag"))));
    }

    public Integer login(SessionView sessionView) {
        return namedParameterJdbcTemplate.update(
                """
                        INSERT INTO sessions (bear_token, usertag)
                        VALUES (:bear_token, :usertag)
                        """,
                new MapSqlParameterSource()
                        .addValue("bear_token", sessionView.bearToken)
                        .addValue("usertag", sessionView.usertag));
    }

    public Integer logout() {
        return namedParameterJdbcTemplate.update(
                "DELETE FROM sessions WHERE session_id = :session_id",
                new MapSqlParameterSource("session_id", user.sessionView.id));
    }

}
