package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.PlatformUser;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.PlatformUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlatformUserService {
    private PlatformUserRepository platformUserRepository;

    public PlatformUserService(PlatformUserRepository platformUserRepository) {
        this.platformUserRepository = platformUserRepository;
    }

    public Optional<PlatformUser> getCurrentUser(String username) {
        return platformUserRepository.findByUserName(username);
    }

}
