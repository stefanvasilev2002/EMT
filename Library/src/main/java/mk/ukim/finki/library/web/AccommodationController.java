package mk.ukim.finki.library.web;
import mk.ukim.finki.library.listeners.NoAccommodationsListener;
import mk.ukim.finki.library.model.Accommodation;
import mk.ukim.finki.library.model.dto.AccommodationDto;
import mk.ukim.finki.library.model.events.NoEntitiesOfCategoryEvent;
import mk.ukim.finki.library.model.exceptions.NoAvailableNights;
import mk.ukim.finki.library.service.AccommodationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accommodations")
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final NoAccommodationsListener noEntitiesOfCategoryEventListener;
    public AccommodationController(AccommodationService accommodationService, NoAccommodationsListener noEntitiesOfCategoryEventListener) {
        this.accommodationService = accommodationService;
        this.noEntitiesOfCategoryEventListener = noEntitiesOfCategoryEventListener;
    }

    @GetMapping("")
    public List<Accommodation> getAllAccommodations()
    {
        return accommodationService.listAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodationById(@PathVariable Long id)
    {
        return accommodationService.findById(id).map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Accommodation> addAccommodation(@RequestBody AccommodationDto accommodationDto)
    {
        return accommodationService.create(accommodationDto.getName(),
                        accommodationDto.getCategory(),
                        accommodationDto.getHostId(),
                        accommodationDto.getAvailableNights())
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Accommodation> deleteAccommodation(@PathVariable Long id)
    {
        return accommodationService.delete(id)
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/edit/{id}")
    public ResponseEntity<Accommodation> editAccommodation(@PathVariable Long id, @RequestBody AccommodationDto accommodationDto)
    {
        return accommodationService.update(id,
                        accommodationDto.getName(),
                        accommodationDto.getCategory(),
                        accommodationDto.getHostId(),
                        accommodationDto.getAvailableNights())
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/lowerNights/{id}")
    public ResponseEntity<Accommodation> lowerAvailableNights(@PathVariable Long id) throws NoAvailableNights {
        return accommodationService.lowerAvailableCopies(id)
                .map(accommodation -> ResponseEntity.ok().body(accommodation))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/filter")
    public ResponseEntity<List<Accommodation>> filterAccommodations(@RequestParam String category) {
        List<Accommodation> filteredAccommodations = accommodationService.filter(category);
        if (filteredAccommodations.isEmpty()) {
            return noEntitiesOfCategoryEventListener.handleNoAccommodationsFound(new NoEntitiesOfCategoryEvent(this, category));
        } else {
            return ResponseEntity.ok(filteredAccommodations);
        }
    }
}
