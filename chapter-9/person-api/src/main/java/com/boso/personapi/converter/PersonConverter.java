package com.boso.personapi.converter;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

  private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

  public static <O, D> D parseObject(O origin, Class<D> destination) {
    return mapper.map(origin, destination);
  }

  public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
    List<D> destinationObjects = new ArrayList<>();
    origin.forEach(e -> add(destinationObjects, destination, e));
    return destinationObjects;
  }

  private static <D, O> void add(final List<D> destinationObjects, final Class<D> destination,
      final O origin) {
    destinationObjects.add(mapper.map(origin, destination));
  }
}
