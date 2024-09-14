package org.chat.infrastructure.migration;

import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Component;

@Component
public class MigrationManger {

    private static final String jdbcUrl = System.getenv("JDBC_URL");
    private static final String databaseUser = System.getenv("DATABASE_USER");
    private static final String databasePassword = System.getenv("DATABASE_PASSWORD");

    public void migrateDatabase() throws Exception {
        Flyway.configure()
            .dataSource(jdbcUrl, databaseUser, databasePassword)
            .load()
            .migrate();
    }

}
