package com.boso.personapi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.boso.personapi.data.dto.PersonDto;
import com.boso.personapi.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Person endpoint", tags = "PersonEndpoint")
@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

  private final PersonService service;

  private final PagedResourcesAssembler<PersonDto> assembler;

  @Autowired
  public PersonController(final PersonService service,
      final PagedResourcesAssembler<PersonDto> assembler) {
    this.service = service;
    this.assembler = assembler;
  }

  @ApiOperation(value = "Find all people")
  @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
  public ResponseEntity<?> findAll(
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "limit", defaultValue = "10") int limit,
      @RequestParam(value = "direction", defaultValue = "asc") String direction) {

    var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

    Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

    Page<PersonDto> peoplePage = service.findAll(pageable);
    peoplePage.forEach(e -> {
      Link link = linkTo(methodOn(PersonController.class).findById(e.getKey())).withSelfRel();
      e.add(link);
    });

    final var entityModels = assembler.toModel(peoplePage);

    return new ResponseEntity(entityModels, HttpStatus.OK);
  }

  @ApiOperation(value = "Find person by name")
  @GetMapping(value = "/findPersonByName/{firstName}",produces = {"application/json", "application/xml", "application/x-yaml"})
  public ResponseEntity<PagedModel<PersonDto>> findPersonByName(
      @PathVariable(value = "firstName") String firstName,
      @RequestParam(value = "page", defaultValue = "0") int page,
      @RequestParam(value = "limit", defaultValue = "10") int limit,
      @RequestParam(value = "direction", defaultValue = "asc") String direction,
      PagedResourcesAssembler<PersonDto> assembler) {

    var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

    Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "firstName"));

    Page<PersonDto> peoplePage = service.findPersonByName(firstName, pageable);
    peoplePage.forEach(e -> {
      Link link = linkTo(methodOn(PersonController.class).findById(e.getKey())).withSelfRel();
      e.add(link);
    });
    final var entityModels = assembler.toModel(peoplePage);

    return new ResponseEntity(entityModels, HttpStatus.OK);
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
