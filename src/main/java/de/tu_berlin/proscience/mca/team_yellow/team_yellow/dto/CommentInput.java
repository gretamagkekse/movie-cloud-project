package de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto;

public class CommentInput {
    private int ratingActors;
    private int ratingStory;
    private int ratingVisuals;
    private String comment;

    // Constructors, Getters and Setters

    public CommentInput() {
    }

    public CommentInput(int ratingActors, int ratingStory,int ratingVisuals, String comment) {
        this.ratingActors = ratingActors;
        this.ratingStory = ratingStory;
        this.ratingVisuals = ratingVisuals;
        this.comment = comment;

    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRatingActors() {
        return ratingActors;
    }

    public void setRatingActors(int rating) {
        this.ratingActors = ratingActors;
    }
    public int getRatingStory() {
        return ratingStory;
    }
    public void setRatingStory(int rating) {
        this.ratingStory = ratingStory;
    }
    public int getRatingVisuals() {
        return ratingVisuals;
    }
    public void setRatingVisuals(int rating) {
        this.ratingVisuals = ratingVisuals;
    }

}
