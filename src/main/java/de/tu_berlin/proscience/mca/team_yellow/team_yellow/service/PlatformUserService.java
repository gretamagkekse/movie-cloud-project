package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.PlatformUser;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.PlatformUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class responsible for handling operations related to platform users.
 */
@Service
public class PlatformUserService {
    private PlatformUserRepository platformUserRepository;

    public PlatformUserService(PlatformUserRepository platformUserRepository) {
        this.platformUserRepository = platformUserRepository;
    }

    /**
     * Retrieves the current user by their username.
     *
     * @param username the username of the user.
     * @return an Optional containing the PlatformUser if found, or empty if not.
     */
    public Optional<PlatformUser> getCurrentUser(String username) {
        return platformUserRepository.findByUserName(username);
    }

}
