package com.boso.personapi.serialization.converter.v1;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

@Component
public final class YamlJackson2HttpMessageConverter extends AbstractJackson2HttpMessageConverter {

  public YamlJackson2HttpMessageConverter() {
    super(new YAMLMapper(), MediaType.parseMediaType("application/x-yaml"));
  }

}
