package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.model.exceptions.InvalidCountryId;
import mk.ukim.finki.library.repository.CountryRepository;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(InvalidCountryId::new);
    }

    @Override
    public Country create(String name, String continent) {
        return countryRepository.save(new Country(name, continent));
    }

    @Override
    public Country update(Long id, String name, String continent) {
        Country country = findById(id);
        country.setName(name);
        country.setContinent(continent);
        return countryRepository.save(country);
    }

    @Override
    public Country delete(Long id) {
        Country country = findById(id);
        countryRepository.delete(country);
        return country;
    }
}
