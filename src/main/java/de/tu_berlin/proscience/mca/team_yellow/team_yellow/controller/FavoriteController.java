package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Movie;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.PlatformUser;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.PlatformUserRepository;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.TMDbService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for managing the favorite movies of users.
 * Provides endpoints to add, remove, list, and check favorite movies for a user.
 */
@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private PlatformUserRepository userRepository;

    @Autowired
    private TMDbService tmdbService;


    /**
     * Adds a movie to the user's list of favorite movies.
     *
     * @param userId  the ID of the user.
     * @param movieId the ID of the movie to add.
     * @return a ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/add")
    @SecurityRequirement(name = "BasicAuth")
    public ResponseEntity<?> addFavorite(@RequestParam String userId, @RequestParam Long movieId) {
        PlatformUser user = userRepository.findByUserName(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getFavoriteMovieIds().contains(movieId)) {
            user.getFavoriteMovieIds().add(movieId);
            userRepository.save(user);
            return ResponseEntity.ok("Movie added to favorites");
        }
        return ResponseEntity.badRequest().body("Movie is already in favorites");
    }


    /**
     * Removes a movie from the user's list of favorite movies.
     *
     * @param userId  the ID of the user.
     * @param movieId the ID of the movie to remove.
     * @return a ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/remove")
    @SecurityRequirement(name = "BasicAuth")
    public ResponseEntity<?> removeFavorite(@RequestParam String userId, @RequestParam Long movieId) {
        PlatformUser user = userRepository.findByUserName(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getFavoriteMovieIds().contains(movieId)) {
            user.getFavoriteMovieIds().remove(movieId);
            userRepository.save(user);
            return ResponseEntity.ok("Movie removed from favorites");
        }
        return ResponseEntity.badRequest().body("Movie not found in favorites");
    }

    /**
     * Retrieves the list of favorite movies for a user.
     *
     * @param userId the ID of the user.
     * @return a list of Movie objects representing the user's favorite movies.
     */
    @GetMapping("/list")
    @SecurityRequirement(name = "BasicAuth")
    public ResponseEntity<List<Movie>> getFavorites(@RequestParam String userId) {
        PlatformUser user = userRepository.findByUserName(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Long> favoriteMovieIds = user.getFavoriteMovieIds();

        List<Movie> movies = favoriteMovieIds.stream()
                .map(movieId -> tmdbService.getMovieDetails(movieId))
                .collect(Collectors.toList());

        return ResponseEntity.ok(movies);
    }

    /**
     * Retrieves the list of favorite movies for a user.
     *
     * @param userId the ID of the user.
     * @return a list of Movie objects representing the user's favorite movies.
     */
    @GetMapping("/is-favorite")
    @SecurityRequirement(name = "BasicAuth")
    public ResponseEntity<Boolean> isFavorite(@RequestParam String userId, @RequestParam Long movieId) {
        PlatformUser user = userRepository.findByUserName(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isFavorite = user.getFavoriteMovieIds().contains(movieId);
        return ResponseEntity.ok(isFavorite);
    }
}
