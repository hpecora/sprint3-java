package com.mottu.mototracker.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;

@Configuration
public class FlywayRepairConfig {

    @Bean
    public FlywayMigrationStrategy repairThenMigrate() {
        return flyway -> {
            // Corrige o histórico (remove marcação de migração falha)
            flyway.repair();
            // Aplica as migrações novamente
            flyway.migrate();
        };
    }
}
