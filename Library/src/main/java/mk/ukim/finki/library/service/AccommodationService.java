package mk.ukim.finki.library.service;

import mk.ukim.finki.library.model.Accommodation;
import mk.ukim.finki.library.model.Category;
import mk.ukim.finki.library.model.exceptions.NoAvailableNights;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {
    List<Accommodation> listAll();
    Optional<Accommodation> findById(Long id);
    Optional<Accommodation> create(String name, Category category, Long hostId, Integer availableNights);
    Optional<Accommodation> update(Long id, String name, Category category, Long hostId, Integer availableNights);
    Optional<Accommodation> delete(Long id);
    Optional<Accommodation> lowerAvailableCopies(Long id) throws NoAvailableNights;
    List<Accommodation> filter(String category);
}
