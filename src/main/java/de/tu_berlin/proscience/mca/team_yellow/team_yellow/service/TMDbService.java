package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Movie;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TMDbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3";

    public String getMovieDetails(String movieId) {
        String url = TMDB_BASE_URL + "/movie/" + movieId + "?api_key=" + apiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        return response.getBody();
    }

    public String getPopularMovies() {
        String url = String.format("%s/movie/popular?api_key=%s", apiUrl, apiKey);
        return restTemplate.getForObject(url, String.class);
    }

    public String searchMovies(String query) {
        String url = String.format("%s/search/movie?api_key=%s&query=%s", apiUrl, apiKey, query);
        return restTemplate.getForObject(url, String.class);
    }

    public Movie getMovieDetails(Long movieId) {
        String url = String.format("%s/movie/%d?api_key=%s", apiUrl, movieId, apiKey);
        try {
            return restTemplate.getForObject(url, Movie.class);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie Not Found");
        }
    }

}
