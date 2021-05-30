package com.boso.personapi.service;

import com.boso.personapi.model.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  private final AtomicLong counter = new AtomicLong();

  public Person create(final Person person) {
    return person;
  }

  public Person update(final Person person) {
    return person;
  }

  public void delete(final String id) {
  }

  public Person findById(final String id) {
    final Person person = new Person();
    person.setId(counter.incrementAndGet());
    person.setFirstName("Felipe");
    person.setLastName("Boso");
    person.setAddress("Rua Java 150");
    person.setGender("Male");
    return person;
  }

  public List<Person> findAll() {
    List<Person> people = new ArrayList<>();
    for (int i = 0; i < 8; i++) {
      people.add(mockPerson(i));
    }
    return people;
  }

  private Person mockPerson(int i) {
    final Person person = new Person();
    person.setId(Long.valueOf(i));
    person.setFirstName("Felipe");
    person.setLastName("Boso");
    person.setAddress("Rua Java 150");
    person.setGender("Male");
    return person;
  }
}
