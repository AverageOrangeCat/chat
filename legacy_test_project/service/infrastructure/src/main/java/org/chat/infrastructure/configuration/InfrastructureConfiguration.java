package org.chat.infrastructure.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class InfrastructureConfiguration {

    private static final String jdbcUrl = System.getenv("JDBC_URL");
    private static final String databaseUser = System.getenv("DATABASE_USER");
    private static final String databasePassword = System.getenv("DATABASE_PASSWORD");

    @Bean
    public DataSource getDataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();

            dataSource.setUrl(jdbcUrl);
            dataSource.setUsername(databaseUser);
            dataSource.setPassword(databasePassword);
            dataSource.setDriverClassName("org.postgresql.Driver");

            return dataSource;
    }

}
