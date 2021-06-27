package com.boso.personapi.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerUiWebMvcConfigurer implements WebMvcConfigurer {

  private static final String baseUrl = "person";

  @Bean
  public Docket api() {
    final var path = "com.boso.personapi.controller";
    final var host = "localhost:8080";

    return new Docket(DocumentationType.SWAGGER_2)
        .host(host)
        .select()
        .apis(RequestHandlerSelectors.basePackage(path))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }

  private ApiInfo apiInfo() {
    return new ApiInfo("Restfull API AWS and Docker",
        "API Course",
        "v1",
        "Terms of service URL",
        new Contact("Felipe Boso", "www.bosoti.com.br", "felipethiagoboso@gmail.com"),
        "Apache 2.0",
        "",
        Collections.emptyList()
    );
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    String baseUrl = StringUtils.trimTrailingCharacter(this.baseUrl, '/');
    registry.
        addResourceHandler(baseUrl + "/swagger-ui/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
        .resourceChain(false);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController(baseUrl + "/swagger-ui/")
        .setViewName("forward:" + baseUrl + "/swagger-ui/index.html");
  }
}
