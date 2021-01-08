package dev.bigwig.fastrest;

import dev.bigwig.fastrest.common.config.FProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableConfigurationProperties(FProperties.class)
@SpringBootApplication
public class FastRestApplication {

  public static void main(String[] args) {
    SpringApplication.run(FastRestApplication.class, args);
  }
}
