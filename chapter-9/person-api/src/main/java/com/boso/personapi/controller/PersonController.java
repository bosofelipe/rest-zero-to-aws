package com.boso.personapi.controller;

import com.boso.personapi.data.dto.PersonDto;
import com.boso.personapi.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

  private final PersonService service;

  @Autowired
  public PersonController(final PersonService service) {
    this.service = service;
  }

  @GetMapping
  public List<PersonDto> findAll() throws Exception {
    return service.findAll();
  }

  @GetMapping(value = "/{id}")
  public PersonDto findById(@PathVariable("id") Long id) throws Exception {
    return service.findById(id);
  }

  @PostMapping
  public PersonDto create(@RequestBody PersonDto person) throws Exception {
    return service.create(person);
  }

  @PutMapping
  public PersonDto update(@RequestBody PersonDto person) throws Exception {
    return service.update(person);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
    service.delete(id);
    return ResponseEntity.ok().build();
  }
}
