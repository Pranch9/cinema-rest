package ru.pranch.cinema.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import java.util.TreeMap;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
  public static final String SECURITY_SCHEME_NAME = "bearerAuth";

  @Bean
  public OpenAPI customOpenApi() {
    return new OpenAPI()
      .addSecurityItem(new SecurityRequirement()
        .addList(SECURITY_SCHEME_NAME))
      .info(new Info()
        .title("Cinema API"));
  }

  @Bean
  public GroupedOpenApi groupOpenApiV1() {
    return GroupedOpenApi.builder()
      .group("v1")
      .pathsToMatch("/api/v1/**")
      .build();
  }

  @Bean
  public OpenApiCustomiser sortSchemasAlphabetically() {
    return openApi -> openApi.getComponents().setSchemas(new TreeMap<>(openApi.getComponents().getSchemas()));
  }
}
