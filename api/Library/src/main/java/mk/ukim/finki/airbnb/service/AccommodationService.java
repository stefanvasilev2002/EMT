package mk.ukim.finki.airbnb.service;

import mk.ukim.finki.airbnb.model.Accommodation;
import mk.ukim.finki.airbnb.model.Category;
import mk.ukim.finki.airbnb.model.exceptions.NoAvailableNights;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> listAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> create(String name, Category category, Long hostId, Integer availableNights);
    Optional<Accommodation> update(Long id, String name, Category category, Long hostId, Integer availableNights);
    Optional<Accommodation> delete(Long id);
    Optional<Accommodation> lowerAvailableNights(Long id) throws NoAvailableNights;
    List<Accommodation> filter(String category);
}
