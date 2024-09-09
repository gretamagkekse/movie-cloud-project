package de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto;

public class RatingInput {
    private int actors;
    private int story;
    private int visuals;
    private String comment;

    public RatingInput(int actors, int story, int visuals, String comment) {
        this.actors = actors;
        this.story = story;
        this.visuals = visuals;
        this.comment = comment;
    }

    public int getActors() {
        return actors;
    }

    public int getStory() {
        return story;
    }

    public int getVisuals() {
        return visuals;
    }

    public String getComment() {
        return comment;
    }

}
