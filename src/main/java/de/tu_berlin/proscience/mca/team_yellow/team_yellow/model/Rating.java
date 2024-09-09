package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int actors;

    @Column(nullable = false)
    private int story;

    @Column(nullable = false)
    private int visuals;

    @Column(nullable = false, length = 4096)
    private String comment;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private PlatformUser author;


    public Rating() {
    }

    public Rating(int actors, int story, int visuals, String comment, PlatformUser author) {
        this.actors = actors;
        this.story = story;
        this.visuals = visuals;
        this.comment = comment;
        this.author = author;
    }

    public Long getId() {
        return id;
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
