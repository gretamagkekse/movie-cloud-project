package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class BlogUser {
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
//    private String firstName;
//    private String lastName;
//    private String mail;

    @JsonIgnore
    private String passwordHash;

    public BlogUser() {
    }


    public Long getId() {
        return Id;
    }

    public String getUserName() {
        return userName;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public List<Comment> getComments() {
        return comments;
    }
}
