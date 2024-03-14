package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.Category;
import mk.ukim.finki.library.model.exceptions.InvalidBookIdException;
import mk.ukim.finki.library.model.exceptions.NoAvailableCopies;
import mk.ukim.finki.library.repository.BookRepository;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(InvalidBookIdException::new);
    }

    @Override
    public Book create(String name, Category category, Long authorId, Integer availableCopies) {
        return bookRepository.save(new Book(name, category, authorService.findById(authorId),availableCopies));
    }

    @Override
    public Book update(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Book book = findById(id);
        book.setName(name);
        book.setCategory(category);
        book.setAuthor(authorService.findById(authorId));
        book.setAvailableCopies(availableCopies);
        return bookRepository.save(book);
    }

    @Override
    public Book delete(Long id) {
        Book book = findById(id);
        bookRepository.delete(book);
        return book;
    }

    @Override
    public Book lowerAvailableCopies(Long id) throws NoAvailableCopies {
        Book book = findById(id);
        if (book.getAvailableCopies() == 0){
            throw new NoAvailableCopies();
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        return bookRepository.save(book);
    }
}
