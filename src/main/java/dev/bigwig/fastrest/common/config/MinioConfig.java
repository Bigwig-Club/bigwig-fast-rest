package dev.bigwig.fastrest.common.config;

import dev.bigwig.fastrest.common.config.FProperties.Minio;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@RequiredArgsConstructor
@Configuration
@ConditionalOnClass(FProperties.class)
@ConditionalOnProperty(prefix = "f.minio", value = "endpoint")
public class MinioConfig {

  private final FProperties fProperties;

  @Bean
  @ConditionalOnMissingBean
  @SneakyThrows
  public MinioClient minioClient() {
    Minio minio = fProperties.getMinio();

    MinioClient minioClient =
        MinioClient.builder()
            .endpoint(minio.getEndpoint())
            .region(minio.getRegion())
            .credentials(minio.getAccessKey(), minio.getSecretKey())
            .build();
    BucketExistsArgs bucketExistsArgs =
        BucketExistsArgs.builder().bucket(minio.getBucket()).build();

    if (!minioClient.bucketExists(bucketExistsArgs)) {
      log.info("f fast rest: bucket {} not exist, creating", minio.getBucket());
      MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(minio.getBucket()).build();
      minioClient.makeBucket(makeBucketArgs);
    }
    return minioClient;
  }
}
