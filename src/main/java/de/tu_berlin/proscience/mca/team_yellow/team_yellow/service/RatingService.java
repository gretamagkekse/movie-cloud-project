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

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }
    public List<Rating> getAllRatingsByModule(Long id) {
        return ratingRepository.findByModule(id);
    }

    public List<Rating> getAllRatingsByUserId(@PathVariable Long id) {
        return ratingRepository.findByUserId(id);
    }

    //moduleId und userId nach implementierung von modules und users hinzufügen!!
    public void addRating(RatingInput ratingInput) {
        Rating rating = new Rating(ratingInput.getDifficulty(),ratingInput.getOrganisation(),ratingInput.getPractical());
        ratingRepository.save(rating);

    }


}
