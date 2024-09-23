package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.TMDbService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for movie-related operations.
 * Provides endpoints to retrieve popular movies and search for movies using the TMDb API.
 */

@RestController
public class MovieController {

    private final TMDbService tmdbService;
    @Value("${tmdb.api.key}")
    private String tmdbApiKey;

    public MovieController(TMDbService tmdbService) {
        this.tmdbService = tmdbService;
    }



    /**
     * Endpoint to provide the TMDB API key to the frontend securely.
     *
     * @return the TMDB API key.
     */
    @GetMapping("/api/tmdb/apikey")
    public ResponseEntity<String> getTmdbApiKey() {
        return ResponseEntity.ok(tmdbApiKey);
    }

    /**
     * Retrieves a list of popular movies.
     *
     * @return a JSON string representing the list of popular movies.
     */
    @GetMapping("/api/movies/popular")
    public String getPopularMovies() {
        return tmdbService.getPopularMovies();
    }


    /**
     * Searches for movies based on the given query string.
     *
     * @param query the search query for finding movies.
     * @return a JSON string representing the search results.
     */
    @GetMapping("/api/movies/search")
    public String searchMovies(@RequestParam String query) {
        return tmdbService.searchMovies(query);
    }

}
