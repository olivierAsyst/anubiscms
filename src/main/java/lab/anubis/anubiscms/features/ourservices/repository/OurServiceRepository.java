package lab.anubis.anubiscms.features.ourservices.repository;

import lab.anubis.anubiscms.features.ourservices.model.OurService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OurServiceRepository extends JpaRepository<OurService, Long> {

    Optional<OurService> findByName(String name);

}
