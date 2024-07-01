package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.BlogUser;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BlogUser getCurrentUser() {
        return userRepository.findByUserName("Tanem").get(); // Assuming that Tanem is in the database
    }

}
