package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {
    //id,display_name, email, degree_id, pw_hash

    //https://github.com/freitagsrunde/modulist/blob/master/db/model-user.go
    //ID, FirstName,LastName, Mail, MailVerified, PasswordHash, StatusGroup,privileges, Enabled
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true)
    private String userName;

    @OneToMany(mappedBy = "author")
    @JsonBackReference
    private List<Comment> comments;

    @OneToMany(mappedBy = "author")
    @JsonBackReference
    private List<Rating> ratings;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private Degree degree;

    @JsonIgnore
    private String passwordHash;

    public User() {
    }

    public User(Long id, String userName, List<Comment> comments, List<Rating> ratings, Degree degree, String passwordHash) {
        this.userName = userName;
    }

    public Long getId() {
        return Id;
    }

    public String getUserName() {
        return userName;
    }
    public List<Comment> getComments() {
        return comments;
    }
}
