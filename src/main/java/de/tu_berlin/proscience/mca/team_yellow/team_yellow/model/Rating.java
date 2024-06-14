package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //auf mehr Kriterien erweiterbar
    @Column(nullable = false)
    private int difficulty;
    @Column(nullable = false)
    private int organisation;
    @Column(nullable = false)
    private int practical;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private User author;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Module module;

    public Rating() {}

    public Rating(int difficulty, int organisation, int practical) {
        this.difficulty = difficulty;
        this.organisation = organisation;
        this.practical = practical;

    }

    public Long getId() {
        return id;
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

                ", difficulty=" + difficulty +
                ", organisation=" + organisation +
                ", practical=" + practical +
                '}';
    }
}
