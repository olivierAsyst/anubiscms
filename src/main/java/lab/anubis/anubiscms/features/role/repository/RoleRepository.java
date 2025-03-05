package lab.anubis.anubiscms.features.role.repository;

import lab.anubis.anubiscms.features.role.model.AppRole;
import lab.anubis.anubiscms.features.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(AppRole appRole);
}
