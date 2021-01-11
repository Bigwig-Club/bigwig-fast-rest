package dev.bigwig.fastrest;

import dev.bigwig.fastrest.common.config.FProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(FProperties.class)
@SpringBootApplication
public class FastRestApplication {

  public static void main(String[] args) {
    SpringApplication.run(FastRestApplication.class, args);
  }
}
