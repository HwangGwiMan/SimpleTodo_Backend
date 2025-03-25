package Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.PostgresDialect;

@Configuration
@EnableJdbcRepositories(basePackages = "Backend")
public class JdbcConfig {
    
    // @Bean
    // public Dialect jdbcDialect() {
    //     return SQLiteDialect.INSTANCE;
    // }

    @Bean
    public PostgresDialect jdbcDialect() {
        return PostgresDialect.INSTANCE;
    }
} 