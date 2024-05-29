package de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto;

public class RatingInput {
    private int difficulty;
    private int organisation;
    private int practical;

    public RatingInput(int difficulty, int organisation, int practical) {
        this.difficulty = difficulty;
        this.organisation = organisation;
        this.practical = practical;
    }
    public int getDifficulty() {
        return difficulty;
    }
    public int getOrganisation() {
        return organisation;
    }
    public int getPractical() {
        return practical;
    }

}
