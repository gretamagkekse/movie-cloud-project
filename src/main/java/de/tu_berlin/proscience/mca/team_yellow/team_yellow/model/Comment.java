package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)  // Reference to the blog_user table
    private BlogUser user;  // Will be auto-set to 'Tanem'

    private Long movieId; // Reference to the movie


    private String content;  // Comment content
    private Double rating;   // Rating from 1.0 to 10.0

    private LocalDateTime createdAt;  // When the comment was created


    public Comment() {
        this.createdAt = LocalDateTime.now();
    }

    public Comment(Long movieId, BlogUser user, String content, Double rating) {
        this.movieId = movieId;
        this.user = user;
        this.content = content;
        this.rating = rating;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BlogUser getUser() {
        return user;
    }

    public void setUser(BlogUser user) {
        this.user = user;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
