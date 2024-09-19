package de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.PlatformUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface managing platform users in the database.
 */
public interface PlatformUserRepository extends JpaRepository<PlatformUser, Long> {

    /**
     * Finds a platform user by their username.
     *
     * @param nameUser the username of the user.
     * @return an Optional containing the PlatformUser if found, empty if not.
     */
    Optional<PlatformUser> findByUserName(String nameUser);
}
