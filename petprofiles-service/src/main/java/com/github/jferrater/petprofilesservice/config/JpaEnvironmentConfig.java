package com.github.jferrater.petprofilesservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories("com.github.jferrater.petprofilesservice.repository")
public class JpaEnvironmentConfig {
}
