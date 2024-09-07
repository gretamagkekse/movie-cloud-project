package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

import jakarta.persistence.*;


@Entity
@Table(name = "blog_user")
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    // Constructors, Getters, and Setters
    public BlogUser() {}

    public BlogUser(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

