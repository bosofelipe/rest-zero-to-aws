package com.boso.personapi.service;

import static java.lang.String.format;

import com.boso.personapi.converter.DozerConverter;
import com.boso.personapi.data.dto.PersonDto;
import com.boso.personapi.data.model.Person;
import com.boso.personapi.exception.ResourceNotFoundException;
import com.boso.personapi.repository.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {

  private final PersonRepository personRepository;
  private final DozerConverter personConverter;

  @Autowired
  public PersonService(final PersonRepository personRepository,
      final DozerConverter personConverter) {
    this.personRepository = personRepository;
    this.personConverter = personConverter;
  }

  public PersonDto create(final PersonDto person) {
    final var entity = personConverter.parseObject(person, Person.class);
    return personConverter.parseObject(personRepository.save(entity), PersonDto.class);
  }

  @Transactional
  public PersonDto disablePerson(final Long id) {
    personRepository.disablePerson(id);
    return this.findById(id);
  }

  public PersonDto update(final PersonDto person) {
    final var p = personRepository.findById(person.getKey())
        .orElseThrow(
            () -> new ResourceNotFoundException(
                format("Person not found with id %d", person.getKey())));

    p.setGender(person.getGender());
    p.setFirstName(person.getFirstName());
    p.setLastName(person.getLastName());
    p.setAddress(person.getAddress());

    return personConverter.parseObject(personRepository.save(p), PersonDto.class);
  }

  public void delete(final Long id) {
    final var p = personRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(
                format("Person not found with id %d", id)));
    personRepository.delete(p);
  }

  public PersonDto findById(final Long id) {
    final var person = personRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(format("Person not found with id %d", id)));
    return personConverter.parseObject(person, PersonDto.class);
  }

  public List<PersonDto> findAll() {
    return personConverter
        .parseListObjects(personRepository.findAll(), PersonDto.class);
  }
}
