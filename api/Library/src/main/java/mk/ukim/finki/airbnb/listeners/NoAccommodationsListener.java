package mk.ukim.finki.airbnb.listeners;

import mk.ukim.finki.airbnb.model.Accommodation;
import mk.ukim.finki.airbnb.model.events.NoEntitiesOfCategoryEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class NoAccommodationsListener {
    @EventListener
    public ResponseEntity<List<Accommodation>> handleNoAccommodationsFound(NoEntitiesOfCategoryEvent event) {
        String category = event.getCategory();
        System.out.printf("No accommodations found for category: %s%n", category);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
    }
}
