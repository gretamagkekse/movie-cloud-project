package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;


public class Rating {

    private Long id;
    private Long moduleId;
    //auf mehr Kriterien erweiterbar
    private int difficulty;
    private int organisation;
    private int practical;

    public Rating(Long id, Long moduleId, int difficulty, int organisation, int practical) {
        this.id = id;
        this.moduleId = moduleId;
        this.difficulty = difficulty;
        this.organisation = organisation;
        this.practical = practical;
    }

    public Long getId() {
        return id;
    }

    public Long getModuleId() {
        return moduleId;
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

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", moduleId=" + moduleId +
                ", difficulty=" + difficulty +
                ", organisation=" + organisation +
                ", practical=" + practical +
                '}';
    }
}
