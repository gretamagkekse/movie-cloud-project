package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private User author = new User("Tanem","Basaraner","tanembasaraner@outlook.com");

    public User getCurrentUser() {
        return author;
    }

}
