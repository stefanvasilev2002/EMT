package mk.ukim.finki.library.model.dto;

import lombok.Data;

@Data
public class AuthorDto {
    String name;
    String surname;
    Long countryId;
}
