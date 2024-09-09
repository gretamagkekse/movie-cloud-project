package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.BlogUser;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<BlogUser> getCurrentUser(String username) {
        return userRepository.findByUserName(username);
    }

}
