package mk.ukim.finki.airbnb.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    Category category;
    @ManyToOne
    Host host;
    Integer availableNights;
    Boolean isAvailable;
    public Accommodation(String name, Category category, Host host, Integer availableNights) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.availableNights = availableNights;
        this.isAvailable = true;
    }

    public Accommodation() {

    }
}
