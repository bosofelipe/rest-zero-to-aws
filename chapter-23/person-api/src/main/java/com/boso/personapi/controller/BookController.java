package com.boso.personapi.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.boso.personapi.data.dto.BookDto;
import com.boso.personapi.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Book endpoint", tags = "BookEndpoint")
@RestController
@RequestMapping("/api/book/v1")
public class BookController {

  private final BookService service;

  @Autowired
  public BookController(final BookService service) {
    this.service = service;
  }

  @ApiOperation(value = "Find All Books")
  @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
  public List<BookDto> findAll() {
    List<BookDto> bookDto = service.findAll();
    bookDto.forEach(e -> {
      Link link = linkTo(methodOn(BookController.class).findById(e.getKey())).withSelfRel();
      e.add(link);
    });
    return bookDto;
  }

  @ApiOperation(value = "Find book by id")
  @GetMapping(value = "/{id}", produces = {"application/json", "application/xml",
      "application/x-yaml"})
  public BookDto findById(@PathVariable("id") Long id) {
    BookDto bookDto = service.findById(id);
    Link link = linkTo(methodOn(BookController.class).findById(bookDto.getKey())).withSelfRel();
    bookDto.add(link);
    return bookDto;
  }

  @ApiOperation(value = "Create a new book")
  @PostMapping(produces = {"application/json", "application/xml",
      "application/x-yaml"}, consumes = {"application/json",
      "application/xml", "application/x-yaml"})
  public BookDto create(@RequestBody BookDto bookDto) throws Exception {
    return service.create(bookDto);
  }

  @ApiOperation(value = "Update book")
  @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes = {
      "application/json",
      "application/xml", "application/x-yaml"})
  public BookDto update(@RequestBody BookDto bookDto) throws Exception {
    return service.update(bookDto);
  }

  @ApiOperation(value = "Delete book by id")
  @DeleteMapping(value = "/{id}")
  public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
    service.delete(id);
    return ResponseEntity.ok().build();
  }
}
