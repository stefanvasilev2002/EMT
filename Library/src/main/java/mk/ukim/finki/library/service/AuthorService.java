package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> listAll();
    Author findById(Long id);
    Author create(String name, String surname, Long countryId);
    Author update(Long id, String name, String surname, Long countryId);
    Author delete(Long id);
}
