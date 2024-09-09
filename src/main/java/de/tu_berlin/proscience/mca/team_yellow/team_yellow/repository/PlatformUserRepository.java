package de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.PlatformUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface PlatformUserRepository extends JpaRepository<PlatformUser, Long> {

    Optional<PlatformUser> findByUserName(String nameUser);
}
