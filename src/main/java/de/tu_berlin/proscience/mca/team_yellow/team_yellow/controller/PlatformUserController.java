package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.PlatformUser;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.PlatformUserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
public class PlatformUserController {

    private PlatformUserService userService;

    public PlatformUserController(PlatformUserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/me")
    @SecurityRequirement(name = "BasicAuth")
    public PlatformUser getCurrentUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Optional<PlatformUser> platformUserOptional = userService.getCurrentUser(user.getUsername());

        if (platformUserOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return platformUserOptional.get();
    }


}
