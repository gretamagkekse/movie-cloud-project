package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Degree {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;

    @ManyToMany(mappedBy = "degrees")
    @JsonManagedReference
    private List<Module> modules;

    @OneToMany(mappedBy = "degree")
    @JsonManagedReference
    private List<User> users;

    public Degree() {}

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
