package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.model.Accommodation;
import mk.ukim.finki.library.model.Category;
import mk.ukim.finki.library.model.exceptions.InvalidHostId;
import mk.ukim.finki.library.model.exceptions.InvalidAccommodationIdException;
import mk.ukim.finki.library.model.exceptions.NoAvailableNights;
import mk.ukim.finki.library.repository.AccommodationRepository;
import mk.ukim.finki.library.service.HostService;
import mk.ukim.finki.library.service.AccommodationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
    }

    @Override
    public List<Accommodation> listAll() {
        return accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return Optional.of(accommodationRepository.findById(id).orElseThrow(InvalidAccommodationIdException::new));
    }

    @Override
    public Optional<Accommodation> create(String name, Category category, Long hostId, Integer availableNights) {
        return Optional.of(accommodationRepository.save(new Accommodation(name,
                category,
                hostService.findById(hostId).orElseThrow(InvalidHostId::new),
                availableNights)));
    }

    @Override
    public Optional<Accommodation> update(Long id, String name, Category category, Long hostId, Integer availableNights) {
        Accommodation accommodation = findById(id).orElseThrow(InvalidHostId::new);
        accommodation.setName(name);
        accommodation.setCategory(category);
        accommodation.setHost(hostService.findById(hostId).orElseThrow(InvalidHostId::new));
        accommodation.setAvailableNights(availableNights);
        return Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public Optional<Accommodation> delete(Long id) {
        Accommodation accommodation = findById(id).orElseThrow(InvalidHostId::new);
        accommodationRepository.delete(accommodation);
        return Optional.of(accommodation);
    }

    @Override
    public Optional<Accommodation> lowerAvailableCopies(Long id) throws NoAvailableNights {
        Accommodation accommodation = findById(id).orElseThrow(InvalidHostId::new);
        if (accommodation.getAvailableNights() == 0){
            throw new NoAvailableNights();
        }
        accommodation.setAvailableNights(accommodation.getAvailableNights() - 1);
        return Optional.of(accommodationRepository.save(accommodation));
    }
}
