package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto.RatingInput;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Rating;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.RatingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
public class RatingController {

    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping(path = "/ratings")
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @PostMapping(path = "/ratings")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "BasicAuth")
    public Rating addRating(@RequestBody RatingInput ratingInput, Authentication authentication) {
        if (ratingInput.getActors() < 1 && ratingInput.getStory() > 5 || ratingInput.getVisuals() < 1 || ratingInput.getActors() > 6 || ratingInput.getStory() < 1 || ratingInput.getVisuals() > 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        User user = (User) authentication.getPrincipal();
        return ratingService.addRating(ratingInput, user.getUsername());
    }

    //    @GetMapping(path = "/ratings/user/{id}")
    //    public List<Rating> getAllRatingsByUser(@PathVariable Long id) {
    //        return ratingService.getAllRatingsByUser(id);
    //    }

    //    @GetMapping(path = "/ratings/{id}")
    //    public List<Rating> getAllRatingsByMovie(@PathVariable Long id) {
    //        return ratingService.getAllRatingsByMovie(id);
    //    }


}
