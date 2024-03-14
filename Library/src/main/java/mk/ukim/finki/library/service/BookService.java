package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.Category;
import mk.ukim.finki.library.model.exceptions.NoAvailableCopies;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    List<Book> listAll();
    Book findById(Long id);
    Book create(String name, Category category, Long authorId, Integer availableCopies);
    Book update(Long id, String name, Category category, Long authorId, Integer availableCopies);
    Book delete(Long id);
    Book lowerAvailableCopies(Long id) throws NoAvailableCopies;
}
