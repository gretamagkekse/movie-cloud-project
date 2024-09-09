package de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto;

public class CommentInput {

    private String username;
    private String content;
    private Double rating;

    // Constructors, Getters and Setters

    public CommentInput() {
    }

    public CommentInput(String username, String content, Double rating) {
        this.username = username;
        this.content = content;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
