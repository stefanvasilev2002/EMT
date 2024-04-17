package mk.ukim.finki.airbnb.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.airbnb.model.Category;
import mk.ukim.finki.airbnb.service.AccommodationService;
import mk.ukim.finki.airbnb.service.CountryService;
import mk.ukim.finki.airbnb.service.HostService;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class DataInitializer {

    private final CountryService countryService;
    private final HostService hostService;
    private final AccommodationService accommodationService;

    public DataInitializer(CountryService countryService, HostService hostService, AccommodationService accommodationService) {
        this.countryService = countryService;
        this.hostService = hostService;
        this.accommodationService = accommodationService;
    }

    Random random = new Random();

    @PostConstruct
    public void fillData()
    {
        if(countryService.listAll().isEmpty())
        {
            for(int i = 0; i < 5; i++)
            {
                countryService.create( "Country" + i, "Europe");
            }
        }

        if(hostService.listAll().isEmpty())
        {
            for(int i = 0; i < 3; i++)
            {
                hostService.create("Name" + i, "Surname" + i, (long)i+1);
            }
        }

        if(accommodationService.listAll().size() < 10)
        {
            for(int i = 0; i < 10; i++)
            {
                accommodationService.create( "Accommodation" + i, Category.values()[random.nextInt(5)], (long) random.nextInt(3) + 1, 10);
            }
        }
    }
}
