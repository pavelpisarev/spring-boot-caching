package ru.banana.example.springbootcaching.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.banana.example.springbootcaching.model.Book;
import ru.banana.example.springbootcaching.repository.BookRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Slf4j
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }
    @Cacheable(value = "books", key = "#isbn") // doesn't save the entity if the given isbn already exists
    public Book create(String title, String isbn) {
        try {
            Thread.sleep(2000);
        } catch(InterruptedException ex) {}
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        log.info("database call");
        return bookRepository.save(book);
    }

    @Cacheable(value = "books", key = "#isbn")
    public Book read(String isbn) {
        try {
            Thread.sleep(2000);
        } catch(InterruptedException ex) {}
        log.info("database call");
        return bookRepository.findByIsbn(isbn).orElseThrow(EntityNotFoundException::new);
    }

    @CachePut(value = "books", key = "#isbn")
    public Book update(String title, String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(EntityNotFoundException::new);
        book.setTitle(title);
        log.info("updating cache and entity");
        return bookRepository.save(book);
    }

    @CacheEvict(value = "books", key = "#isbn")
    @Transactional
    public Integer delete(String isbn) {
        log.info("removing from cache and database");
        return bookRepository.deleteByIsbn(isbn);
    }
}
