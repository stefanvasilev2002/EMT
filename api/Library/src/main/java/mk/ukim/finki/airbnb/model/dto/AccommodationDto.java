package mk.ukim.finki.airbnb.model.dto;

import lombok.Data;
import mk.ukim.finki.airbnb.model.Category;

@Data
public class AccommodationDto {
    String name;
    Category category;
    Long hostId;
    Integer availableNights;
}
