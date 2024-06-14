package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Module {
    //id, title,....siehe API
    // ID,Title,CourseType,CourseID,CreditHours,Annotation,Content,CourseURL,DetailedDescription,Requirements,Audience ,Comment,CourseAssessment ,Literature,TeachingContents,Cycle
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String content;

    @ManyToMany(mappedBy = "modules")
    @JsonManagedReference
    private List<Degree> degrees;

    @OneToMany(mappedBy = "module")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "module")
    private List<Comment> comments ;






    public Module() {}
    public Module(String title, String content) {
        this.title = title;
        this.content = content;
    }
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
