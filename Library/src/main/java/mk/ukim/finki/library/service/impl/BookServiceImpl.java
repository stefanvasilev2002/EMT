package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.Category;
import mk.ukim.finki.library.model.exceptions.InvalidAuthorId;
import mk.ukim.finki.library.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.library.model.exceptions.NoAvailableCopies;
import mk.ukim.finki.library.repository.BookRepository;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.of(bookRepository.findById(id).orElseThrow(InvalidBookIdException::new));
    }

    @Override
    public Optional<Book> create(String name, Category category, Long authorId, Integer availableCopies) {
        return Optional.of(bookRepository.save(new Book(name,
                category,
                authorService.findById(authorId).orElseThrow(InvalidAuthorId::new),
                availableCopies)));
    }

    @Override
    public Optional<Book> update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = findById(id).orElseThrow(InvalidAuthorId::new);
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(authorService.findById(authorId).orElseThrow(InvalidAuthorId::new));
        book.setAvailableCopies(availableCopies);
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> delete(Long id) {
        Book book = findById(id).orElseThrow(InvalidAuthorId::new);
        bookRepository.delete(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> lowerAvailableCopies(Long id) throws NoAvailableCopies {
        Book book = findById(id).orElseThrow(InvalidAuthorId::new);
        if (book.getAvailableCopies() == 0){
            throw new NoAvailableCopies();
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        return Optional.of(bookRepository.save(book));
    }
}
