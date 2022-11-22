package ru.banana.example.springbootcaching.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.banana.example.springbootcaching.model.Book;
import ru.banana.example.springbootcaching.repository.BookRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }
    public Book create(String title, String isbn) {
        Book book = new Book();
        book.setTitle(title);
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }

    public Book read(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(()->new EntityNotFoundException("Not found"));
    }

    public Book update(String title, String isbn) {
        Book book = bookRepository.findByIsbn(isbn).orElseThrow(()->new EntityNotFoundException("Not found"));
        book.setTitle(title);
        return bookRepository.save(book);
    }

    public Book delete(String isbn) {
        return bookRepository.deleteByIsbn(isbn).orElseThrow(()->new EntityNotFoundException("Not found"));
    }
}
