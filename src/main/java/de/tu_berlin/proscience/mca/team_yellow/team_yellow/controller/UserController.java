package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.BlogUser;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    //getCurrentUser +
    //getUserById

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/me")
    public BlogUser getCurrentUser() {
        return userService.getCurrentUser();
    }


}
