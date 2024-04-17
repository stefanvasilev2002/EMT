package mk.ukim.finki.airbnb.service.impl;

import mk.ukim.finki.airbnb.model.Accommodation;
import mk.ukim.finki.airbnb.model.Category;
import mk.ukim.finki.airbnb.model.exceptions.InvalidHostId;
import mk.ukim.finki.airbnb.model.exceptions.InvalidAccommodationIdException;
import mk.ukim.finki.airbnb.model.exceptions.NoAvailableNights;
import mk.ukim.finki.airbnb.repository.AccommodationRepository;
import mk.ukim.finki.airbnb.service.HostService;
import mk.ukim.finki.airbnb.service.AccommodationService;
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
        return accommodationRepository.findAll().stream().filter(Accommodation::getIsAvailable).toList();
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
    public Optional<Accommodation> lowerAvailableNights(Long id) throws NoAvailableNights {
        Accommodation accommodation = findById(id).orElseThrow(InvalidHostId::new);
        if (accommodation.getAvailableNights() == 0 || !accommodation.getIsAvailable()){
            throw new NoAvailableNights();
        }
        accommodation.setAvailableNights(accommodation.getAvailableNights() - 1);
        accommodation.setIsAvailable(false);
        return Optional.of(accommodationRepository.save(accommodation));
    }

    @Override
    public List<Accommodation> filter(String category) {
        return listAll().stream().filter(x->x.getCategory().name().equals(category)).toList();
    }
}
