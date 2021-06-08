package com.boso.personapi.config;

import com.boso.personapi.serialization.converter.YamlJackson2HttpMessageConverter;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private static final MediaType MEDIA_TYPE_YAML = MediaType.valueOf("application/x-yaml");

  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new YamlJackson2HttpMessageConverter());
  }

  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS", "HEAD", "TRACE",
            "CONNECT");
  }

  @Override
  public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
    configurer.favorPathExtension(false).
        favorParameter(false).
        ignoreAcceptHeader(false).
        useRegisteredExtensionsOnly(false).
        defaultContentType(MediaType.APPLICATION_JSON).
        mediaType("xml", MediaType.APPLICATION_XML).
        mediaType("json", MediaType.APPLICATION_JSON).
        mediaType("x-yaml", MEDIA_TYPE_YAML);
  }
}
