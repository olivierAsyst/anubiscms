package lab.anubis.anubiscms.features.user.repository;

import lab.anubis.anubiscms.features.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String username);

    boolean existsByEmail(String email);
}
