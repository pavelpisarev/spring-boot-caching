package ru.banana.example.springbootcaching.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.banana.example.springbootcaching.model.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    Integer deleteByIsbn(String isbn);
}
