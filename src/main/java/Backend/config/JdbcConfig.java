package Backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

@Configuration
@EnableJdbcRepositories(basePackages = "Backend")
public class JdbcConfig {
    
    @Bean
    public Dialect jdbcDialect(NamedParameterJdbcOperations operations) {
        return SQLiteDialect.INSTANCE;
    }
} 