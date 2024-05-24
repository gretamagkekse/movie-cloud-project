package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto.RatingInput;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Rating;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
public class RatingController {

    private RatingService ratingService;
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping(path="/ratings")
    public List<Rating> getAllRatings() {
        return ratingService.getAllRatings();
    }

    @GetMapping(path="/ratings/{id}")
    public List<Rating> getAllRatingsByModule(@PathVariable Long id) {
        return ratingService.getAllRatingsByModule(id);
    }

    @PostMapping(path="/ratings")
    public void addRating(@RequestBody RatingInput ratingInput) {
        if (ratingInput.getDifficulty() < 1 && ratingInput.getDifficulty() > 5 || ratingInput.getPractical() < 1 || ratingInput.getPractical() > 6 || ratingInput.getOrganisation()< 1 || ratingInput.getOrganisation() > 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
         ratingService.addRating(ratingInput);
    }
    @GetMapping(path="/ratings/user/{id}")
    public List<Rating> getAllRatingsByUserId(@PathVariable Long id) {
        return ratingService.getAllRatingsByUserId(id);
    }
    //deleteRating

}
