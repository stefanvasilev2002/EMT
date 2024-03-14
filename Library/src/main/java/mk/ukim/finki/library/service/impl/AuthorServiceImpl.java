package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.exceptions.InvalidAuthorId;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryService countryService;
    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(InvalidAuthorId::new);
    }

    @Override
    public Author create(String name, String surname, Long countryId) {
        return authorRepository.save(new Author(name, surname, countryService.findById(countryId)));
    }

    @Override
    public Author update(Long id, String name, String surname, Long countryId) {
        Author author = findById(id);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(countryService.findById(id));
        return authorRepository.save(author);
    }

    @Override
    public Author delete(Long id) {
        Author author = findById(id);
        authorRepository.deleteById(id);
        return author;
    }
}
