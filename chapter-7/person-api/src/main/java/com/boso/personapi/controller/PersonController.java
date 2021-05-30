package com.boso.personapi.controller;

import com.boso.personapi.model.Person;
import com.boso.personapi.service.PersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

  private final PersonService service;

  @Autowired
  public PersonController(final PersonService service) {
    this.service = service;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Person> findAll() throws Exception {
    return service.findAll();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public Person findById(@PathVariable("id") String id) throws Exception {
    return service.findById(id);
  }


  @RequestMapping(method = RequestMethod.POST,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Person create(@RequestBody Person person) throws Exception {
    return service.create(person);
  }

  @RequestMapping(method = RequestMethod.PUT,
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public Person update(@RequestBody Person person) throws Exception {
    return service.update(person);
  }

  @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
  public void delete(@PathVariable("id") String id) throws Exception {
    service.delete(id);
  }

}
