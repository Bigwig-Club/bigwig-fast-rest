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

  /**
   * 忽略权限校验的路径
   */
  private List<String> ignoredPath;

  /**
   * Minio 对象存储配置
   */
  private Minio minio = new Minio();

  @Data
  @AllArgsConstructor
  @NoArgsConstructor
  public static class Minio {

    /**
     * 端点地址
     */
    private String endpoint;

    /**
     * 区域，随意配
     */
    private String region;

    /**
     * 存储桶名称
     */
    private String bucket;

    /**
     * Access Key
     */
    private String accessKey;

    /**
     * Secret Key
     */
    private String secretKey;
  }
}
