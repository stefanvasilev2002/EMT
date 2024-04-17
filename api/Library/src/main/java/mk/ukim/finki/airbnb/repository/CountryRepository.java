package mk.ukim.finki.airbnb.repository;

import mk.ukim.finki.airbnb.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
