package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;

/**
 * Class representing a movie.
 */
public class Movie {

    private Long id;
    private String title;
    private String overview;

    @JsonProperty("poster_path")
    private String posterPath;

    @JsonProperty("vote_average")
    private Double voteAverage;

    // Getters and Setters for each field

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    // Helper method to get the full image URL
    public String getFullPosterPath() {
        return "https://image.tmdb.org/t/p/w500" + this.posterPath;
    }
}
