package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int ratingActors;
    private int ratingStory;
    private int ratingVisuals;

    private String comment;

    private Long movieId; // Reference to the movie

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Reference to the blog_user table
    private PlatformUser user;

    private LocalDateTime createdAt;  // When the comment was created

    public Comment() {
        this.createdAt = LocalDateTime.now();
    }

    public Comment(int ratingActors, int ratingStory, int ratingVisuals, String comment, Long movieId, PlatformUser user) {
        this.ratingActors = ratingActors;
        this.ratingStory = ratingStory;
        this.ratingVisuals = ratingVisuals;
        this.comment = comment;
        this.movieId = movieId;
        this.user = user;
        this.createdAt = LocalDateTime.now();
        System.out.println("Comment created"+ ratingVisuals+"/"+ratingActors+"/"+ratingStory);
    }

    public int getRatingActors() {
        return ratingActors;
    }

    public void setRatingActors(int ratingActors) {
        this.ratingActors = ratingActors;
    }

    public int getRatingStory() {
        return ratingStory;
    }

    public void setRatingStory(int ratingStory) {
        this.ratingStory = ratingStory;
    }

    public int getRatingVisuals() {
        return ratingVisuals;
    }

    public void setRatingVisuals(int ratingVisuals) {
        this.ratingVisuals = ratingVisuals;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public PlatformUser getUser() {
        return user;
    }

    public void setUser(PlatformUser user) {
        this.user = user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
