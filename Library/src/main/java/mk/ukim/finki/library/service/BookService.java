package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.Category;
import mk.ukim.finki.library.model.exceptions.NoAvailableCopies;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();
    Optional<Book> findById(Long id);
    Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies);
    Optional<Book> delete(Long id);
    Optional<Book> lowerAvailableCopies(Long id) throws NoAvailableCopies;
}
