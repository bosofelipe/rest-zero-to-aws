package com.boso.personapi.service;

import com.boso.personapi.exception.ResourceNotFoundException;
import com.boso.personapi.model.Person;
import com.boso.personapi.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  private final PersonRepository personRepository;

  @Autowired
  public PersonService(final PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  public Person create(final Person person) {
    return personRepository.save(person);
  }

  public Person update(final Person person) {
    final var p = personRepository.findById(person.getId())
        .orElseThrow(
            () -> new ResourceNotFoundException(
                String.format("Person not found with id %d", person.getId())));

    p.setGender(person.getGender());
    p.setFirstName(person.getFirstName());
    p.setLastName(person.getLastName());
    p.setAddress(person.getAddress());

    return personRepository.save(p);
  }

  public void delete(final Long id) {
    final var p = personRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(
                String.format("Person not found with id %d", id)));
    personRepository.delete(p);
  }

  public Person findById(final Long id) {
    return personRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(String.format("Person not found with id %d", id)));
  }

  public List<Person> findAll() {
    return personRepository.findAll();
  }
}
