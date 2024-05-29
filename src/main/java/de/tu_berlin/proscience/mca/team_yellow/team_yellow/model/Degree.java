package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

public class Degree {
    //id, title,....siehe API
    private Long id;
    private String title;

    public Degree(Long id, String title) {
        this.id = id;
        this.title = title;
    }
    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
}
