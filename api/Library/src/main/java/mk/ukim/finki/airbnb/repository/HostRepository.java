package mk.ukim.finki.airbnb.repository;

import mk.ukim.finki.airbnb.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
}
