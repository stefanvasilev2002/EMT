package mk.ukim.finki.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;

@Entity
@Data
public class Country {
    @Id
    @GeneratedValue
    Long id;
    String name;
    String continent;

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public Country() {

    }
}
