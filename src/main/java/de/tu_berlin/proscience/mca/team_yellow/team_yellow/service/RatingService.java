package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto.RatingInput;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Rating;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Long.parseLong;

@Service
public class RatingService {
    private List<Rating> ratings = new ArrayList<>();
    private Long nextId = 1L;

    public List<Rating> getAllRatings() {
        return this.ratings;
    }
    public List<Rating> getAllRatingsByModule(Long id) {
        return ratings.stream().filter(rating -> rating.getModuleId() == id).toList();
    }
    public List<Rating> getAllRatingsByUserId(@PathVariable Long id) {
        return ratings.stream().filter(rating -> rating.getUserId() == id).toList();
    }

    //moduleId und userId nach implementierung von modules und users hinzufügen!!
    public void addRating(RatingInput ratingInput) {
        Rating rating = new Rating(getNextEntryId(),8829L,12L,ratingInput.getDifficulty(),ratingInput.getOrganisation(),ratingInput.getPractical());
        this.ratings.add(rating);

    }


    private Long getNextEntryId() {
        return nextId++;
    }
}
