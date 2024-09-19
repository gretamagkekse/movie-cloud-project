package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class PlatformUser {

    //https://github.com/freitagsrunde/modulist/blob/master/db/model-user.go
    //ID, FirstName,LastName, Mail, MailVerified, PasswordHash, StatusGroup,privileges, Enabled
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    // Liste der Movie-IDs (TMDb IDs)
    @ElementCollection
    @CollectionTable(name = "user_favorite_movies", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "movie_id")
    private List<Long> favoriteMovieIds;


    @JsonIgnore
    private String passwordHash;

    public PlatformUser() {
    }
    public PlatformUser(String userName) {
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

    public String getPasswordHash() {
        return passwordHash;
    }

    public List<Long> getFavoriteMovieIds() {
        return favoriteMovieIds;
    }

    public void setFavoriteMovieIds(List<Long> favoriteMovieIds) {
        this.favoriteMovieIds = favoriteMovieIds;
    }


}
