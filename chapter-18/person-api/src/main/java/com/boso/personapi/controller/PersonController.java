package com.boso.personapi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.boso.personapi.data.dto.PersonDto;
import com.boso.personapi.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Person endpoint", tags = "PersonEndpoint")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

  private final PersonService service;

  @Autowired
  public PersonController(final PersonService service) {
    this.service = service;
  }

  @ApiOperation(value = "Find all people")
  @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
  public List<PersonDto> findAll() {
    List<PersonDto> peopleDto = service.findAll();
    peopleDto.forEach(e -> {
      Link link = linkTo(methodOn(PersonController.class).findById(e.getKey())).withSelfRel();
      e.add(link);
    });
    return peopleDto;
  }

  @ApiOperation(value = "Find person by id")
  @GetMapping(value = "/{id}", produces = {"application/json", "application/xml",
      "application/x-yaml"})
  public PersonDto findById(@PathVariable("id") Long id) {
    PersonDto personDto = service.findById(id);
    Link link = linkTo(methodOn(PersonController.class).findById(personDto.getKey())).withSelfRel();
    personDto.add(link);
    return personDto;
  }

  @ApiOperation(value = "Disabled person by id")
  @PatchMapping(value = "/{id}", produces = {"application/json", "application/xml",
      "application/x-yaml"})
  public PersonDto disablePerson(@PathVariable("id") Long id) {
    PersonDto personDto = service.disablePerson(id);
    Link link = linkTo(methodOn(PersonController.class).findById(personDto.getKey())).withSelfRel();
    personDto.add(link);
    return personDto;
  }

  @ApiOperation(value = "Create a person")
  @PostMapping(produces = {"application/json", "application/xml",
      "application/x-yaml"}, consumes = {"application/json",
      "application/xml", "application/x-yaml"})
  public PersonDto create(@RequestBody PersonDto person) throws Exception {
    return service.create(person);
  }

  @ApiOperation(value = "Update person")
  @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {
      "application/json",
      "application/xml", "application/x-yaml"})
  public PersonDto update(@RequestBody PersonDto person) throws Exception {
    return service.update(person);
  }

  @ApiOperation(value = "Delete person by id")
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
    service.delete(id);
    return ResponseEntity.ok().build();
  }
}
