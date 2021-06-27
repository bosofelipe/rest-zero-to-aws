package com.boso.personapi.serialization.converter;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public final class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

  public YamlJackson2HttpMessageConverter() {
    super(new YAMLMapper().setSerializationInclusion(
        Include.NON_NULL
    ), MediaType.parseMediaType("application/x-yaml"));
  }

}
