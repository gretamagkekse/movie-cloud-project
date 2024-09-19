package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Movie;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.TMDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for managing movie details retrieved from the TMDb API.
 */
@RestController
public class MovieDetailController {

    private final TMDbService tmdbService;

    @Autowired
    public MovieDetailController(TMDbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    /**
     * Retrieves movie details by the provided movie ID.
     *
     * @param movieId the ID of the movie.
     * @return the Movie object containing details about the movie.
     */
    @GetMapping("/api/movie/{id}")
    public Movie getMovieDetails(@PathVariable("id") Long movieId) {
        return tmdbService.getMovieDetails(movieId);  // Calls TMDb API to get movie details
    }
}
