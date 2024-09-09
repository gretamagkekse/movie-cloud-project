package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.BlogUser;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class UserController {

    //getCurrentUser +
    //getUserById

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/me")
    @SecurityRequirement(name = "BasicAuth")
    public BlogUser getCurrentUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Optional<BlogUser> blogUserOptional = userService.getCurrentUser(user.getUsername());
        if(blogUserOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return blogUserOptional.get();
    }


}
