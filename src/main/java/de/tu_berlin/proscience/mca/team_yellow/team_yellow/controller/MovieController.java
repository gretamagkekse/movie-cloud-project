package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.TMDbService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private final TMDbService tmdbService;

    public MovieController(TMDbService tmdbService) {
        this.tmdbService = tmdbService;
    }

    @GetMapping("/api/movies/popular")
    public String getPopularMovies() {
        return tmdbService.getPopularMovies();
    }

    @GetMapping("/api/movies/search")
    public String searchMovies(@RequestParam String query) {
        return tmdbService.searchMovies(query);
    }


}
