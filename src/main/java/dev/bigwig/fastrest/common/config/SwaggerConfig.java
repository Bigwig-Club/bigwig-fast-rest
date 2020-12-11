package dev.bigwig.fastrest.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableKnife4j
public class SwaggerConfig {

  @Bean("userDocker")
  public Docket userDocket() {
    return new Docket(DocumentationType.OAS_30)
      .apiInfo(getApiInfo())
      .groupName("用户管理")
      .select()
      .apis(RequestHandlerSelectors.basePackage("dev.bigwig.fastrest.module.user.controller"))
      .paths(PathSelectors.any())
      .build();
  }

  private ApiInfo getApiInfo() {
    return new ApiInfoBuilder()
      .description("Bigwig fast rest.")
      .contact(new Contact("m01i0ng", "", "alley.ma@qq.com"))
      .version("1.0.0")
      .build();
  }
}
