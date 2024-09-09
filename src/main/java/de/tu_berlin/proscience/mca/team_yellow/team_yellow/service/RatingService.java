package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto.RatingInput;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Rating;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.RatingRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@Service
public class RatingService {
    private RatingRepository ratingRepository;
    private PlatformUserService userService;

    public RatingService(RatingRepository ratingRepository, PlatformUserService userService) {
        this.ratingRepository = ratingRepository;
        this.userService = userService;
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public Rating addRating(RatingInput ratingInput, String username) {
        Rating rating = new Rating(
                ratingInput.getActors(),
                ratingInput.getStory(),
                ratingInput.getVisuals(),
                ratingInput.getComment(),
                userService.getCurrentUser(username).get()
        );
        return ratingRepository.save(rating);
    }

    //    public List<Rating> getAllRatingsByMovie(Long id) {
    //        return ratings.stream().filter(rating -> rating.getModuleId() == id).toList();
    //    }
    //    public List<Rating> getAllRatingsByUser(@PathVariable Long id) {
    //        return ratings.stream().filter(rating -> rating.getUserId() == id).toList();
    //    }

}
