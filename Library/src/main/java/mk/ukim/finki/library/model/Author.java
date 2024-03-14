package mk.ukim.finki.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
public class Author {
    @Id
    Long id;
    String name;
    String surname;
    @ManyToOne
    Country country;

    public Author(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Author() {

    }
}
