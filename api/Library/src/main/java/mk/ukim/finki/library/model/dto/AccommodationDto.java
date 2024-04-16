package mk.ukim.finki.library.model.dto;

import lombok.Data;
import mk.ukim.finki.library.model.Category;

@Data
public class AccommodationDto {
    String name;
    Category category;
    Long hostId;
    Integer availableNights;
}
