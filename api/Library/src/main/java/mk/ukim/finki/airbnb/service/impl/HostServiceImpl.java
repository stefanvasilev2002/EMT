package mk.ukim.finki.airbnb.service.impl;

import mk.ukim.finki.airbnb.model.Host;
import mk.ukim.finki.airbnb.model.exceptions.InvalidHostId;
import mk.ukim.finki.airbnb.model.exceptions.InvalidCountryId;
import mk.ukim.finki.airbnb.repository.HostRepository;
import mk.ukim.finki.airbnb.service.HostService;
import mk.ukim.finki.airbnb.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;
    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
    }

    @Override
    public List<Host> listAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return Optional.of(hostRepository.findById(id).orElseThrow(InvalidHostId::new));
    }

    @Override
    public Optional<Host> create(String name, String surname, Long countryId) {
        return Optional.of(hostRepository.save(new Host(name, surname, countryService.findById(countryId).orElseThrow(InvalidCountryId::new))));
    }

    @Override
    public Optional<Host> update(Long id, String name, String surname, Long countryId) {
        Host host = findById(id).orElseThrow(InvalidHostId::new);
        host.setName(name);
        host.setSurname(surname);
        host.setCountry(countryService.findById(id).orElseThrow(InvalidCountryId::new));
        return Optional.of(hostRepository.save(host));
    }

    @Override
    public Optional<Host> delete(Long id) {
        Host host = findById(id).orElseThrow(InvalidHostId::new);
        hostRepository.deleteById(id);
        return Optional.of(host);
    }
}
