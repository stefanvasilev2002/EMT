package mk.ukim.finki.airbnb.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class NoEntitiesOfCategoryEvent extends ApplicationEvent{
    private final String category;

    public NoEntitiesOfCategoryEvent(Object source, String category) {
        super(source);
        this.category = category;
    }
}
