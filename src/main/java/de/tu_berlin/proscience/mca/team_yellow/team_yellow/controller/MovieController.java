package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Movie;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/movies")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }


    @GetMapping(path = "/movies/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        Optional<Movie> movieOptional = movieService.getMovieById(id);
        if (movieOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return movieOptional.get();
    }

}
