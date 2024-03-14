package mk.ukim.finki.library.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    Category category;
    @ManyToOne
    Author author;
    Integer availableCopies;

    public Book(String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;
    }

    public Book() {

    }
}
