package dev.bigwig.fastrest.common.config;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "f")
public class FProperties {

  private List<String> ignoredPath;

  private Minio minio = new Minio();

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Minio {

    private String endpoint;

    private String region;

    private String bucket;

    private String accessKey;

    private String secretKey;
  }
}
