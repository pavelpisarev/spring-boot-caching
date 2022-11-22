package ru.banana.example.springbootcaching.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.banana.example.springbootcaching.dto.BookDto;
import ru.banana.example.springbootcaching.model.Book;
import ru.banana.example.springbootcaching.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAll());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookDto bookDto) {
        return ResponseEntity.ok(bookService.create(bookDto.title, bookDto.isbn));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.read(isbn));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Book> update(@RequestBody String title, @PathVariable String isbn) {
        return ResponseEntity.ok(bookService.update(title, isbn));
    }

    @DeleteMapping("/isbn")
    public ResponseEntity<Book> deleteByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(bookService.delete(isbn));
    }
}
