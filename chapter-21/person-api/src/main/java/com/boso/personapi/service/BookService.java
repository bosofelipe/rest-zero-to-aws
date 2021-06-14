package com.boso.personapi.service;

import static java.lang.String.format;

import com.boso.personapi.converter.DozerConverter;
import com.boso.personapi.data.dto.BookDto;
import com.boso.personapi.data.model.Book;
import com.boso.personapi.exception.ResourceNotFoundException;
import com.boso.personapi.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;
  private final DozerConverter dozerConverter;

  @Autowired
  public BookService(
      final BookRepository bookRepository,
      final DozerConverter dozerConverter) {
    this.bookRepository = bookRepository;
    this.dozerConverter = dozerConverter;
  }

  public BookDto create(final BookDto book) {
    final var entity = dozerConverter.parseObject(book, Book.class);
    return dozerConverter.parseObject(bookRepository.save(entity), BookDto.class);
  }

  public BookDto update(final BookDto book) {
    final var bookEntity = bookRepository.findById(book.getKey())
        .orElseThrow(
            () -> new ResourceNotFoundException(
                format("Book not found with id %d", book.getKey())));

    bookEntity.setAuthor(book.getAuthor());
    bookEntity.setLaunchDate(book.getLaunchDate());
    bookEntity.setPrice(book.getPrice());
    bookEntity.setTitle(book.getTitle());

    return dozerConverter.parseObject(bookRepository.save(bookEntity), BookDto.class);
  }

  public void delete(final Long id) {
    final var p = bookRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(
                format("Book not found with id %d", id)));
    bookRepository.delete(p);
  }

  public BookDto findById(final Long id) {
    final var book = bookRepository.findById(id)
        .orElseThrow(
            () -> new ResourceNotFoundException(format("Book not found with id %d", id)));
    return dozerConverter.parseObject(book, BookDto.class);
  }

  public List<BookDto> findAll() {
    return dozerConverter
        .parseListObjects(bookRepository.findAll(), BookDto.class);
  }
}
