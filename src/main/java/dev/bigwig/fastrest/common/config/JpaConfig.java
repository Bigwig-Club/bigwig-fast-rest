package dev.bigwig.fastrest.common.config;

import dev.bigwig.fastrest.module.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@RequiredArgsConstructor
@Configuration
@EnableJpaAuditing
public class JpaConfig {

  private final UserRepository userRepository;

  @Bean
  public AuditorAware<Long> auditorAware() {
    return new FAuditorAware(userRepository);
  }
}
