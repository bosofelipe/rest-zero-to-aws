package com.boso.personapi.converter;

import com.boso.personapi.converter.mocks.MockPerson;
import com.boso.personapi.converter.v1.DozerConverter;
import com.boso.personapi.data.dto.v1.PersonDto;
import com.boso.personapi.data.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonConverterTest {

  private MockPerson mockPerson = new MockPerson();

  @Test
  void entityToDto() {
    PersonDto personDto = DozerConverter.parseObject(mockPerson.mockEntity(), PersonDto.class);
    Assertions.assertEquals(personDto.getKey(), 0L);
  }

  @Test
  void dtoToEntity() {
    Person person = DozerConverter.parseObject(mockPerson.mockDto(), Person.class);
    Assertions.assertEquals(person.getId(), 0L);
  }

}
