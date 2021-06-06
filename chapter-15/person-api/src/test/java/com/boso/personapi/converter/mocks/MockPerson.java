package com.boso.personapi.converter.mocks;

import com.boso.personapi.data.dto.v1.PersonDto;
import com.boso.personapi.data.model.Person;
import java.util.ArrayList;
import java.util.List;

public class MockPerson {

  public Person mockEntity() {
    return mockEntity(0);
  }

  public PersonDto mockDto() {
    return mockDto(0);
  }

  public List<Person> mockEntityList() {
    List<Person> people = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      people.add(mockEntity());
    }
    return people;
  }

  public List<PersonDto> mockDtoList() {
    List<PersonDto> people = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      people.add(mockDto(i));
    }
    return people;
  }

  private Person mockEntity(final Integer number) {
    Person person = new Person();
    person.setAddress("Rua das Palmeiras");
    person.setFirstName("Ruan");
    person.setLastName("Palmeiras");
    person.setGender((number % 2 == 0) ? "Male" : "Female");
    person.setId(number.longValue());
    return person;
  }

  private PersonDto mockDto(final Integer number) {
    PersonDto person = new PersonDto();
    person.setAddress("Rua Vasco da Gama");
    person.setFirstName("Ruan");
    person.setLastName("Vasco da Gama");
    person.setGender((number % 2 == 0) ? "Male" : "Female");
    person.setKey(number.longValue());
    return person;
  }

}
