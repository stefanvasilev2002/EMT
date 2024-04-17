package mk.ukim.finki.airbnb.model.dto;

import lombok.Data;

@Data
public class HostDto {
    String name;
    String surname;
    Long countryId;
}
