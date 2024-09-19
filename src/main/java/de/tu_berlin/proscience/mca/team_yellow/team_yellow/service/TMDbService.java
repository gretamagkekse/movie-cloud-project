package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

//Service class for the TMDb API
@Service
public class TMDbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3";


    /**
     * Retrieves movie details by the given movie ID as a String.
     *
     * @param movieId ID of the movie.
     * @return Movie details as a String.
     */
    public String getMovieDetails(String movieId) {
        String url = TMDB_BASE_URL + "/movie/" + movieId + "?api_key=" + apiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    /**
     * Retrieves a list of popular movies as a String. This will be shown on the Start page.
     *
     * @return Popular movies as a String.
     */
    public String getPopularMovies() {
        String url = String.format("%s/movie/popular?api_key=%s", apiUrl, apiKey);
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * Searches for movies by the given query string.
     *
     * @param query The search query string.
     * @return Search results as a String.
     */
    public String searchMovies(String query) {
        String url = String.format("%s/search/movie?api_key=%s&query=%s", apiUrl, apiKey, query);
        return restTemplate.getForObject(url, String.class);
    }

    /**
     * Retrieves movie details by the given movie ID as a Movie object.
     *
     * @param movieId ID of the movie.
     * @return Movie details as a Movie object.
     * @throws ResponseStatusException if the movie is not found.
     */
    public Movie getMovieDetails(Long movieId) {
        String url = String.format("%s/movie/%d?api_key=%s", apiUrl, movieId, apiKey);
        try {
            return restTemplate.getForObject(url, Movie.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found");
        }
    }

}
