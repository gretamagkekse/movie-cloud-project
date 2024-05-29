package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

public class User {
    //id,display_name, email, degree_id, pw_hash

    //https://github.com/freitagsrunde/modulist/blob/master/db/model-user.go
    //ID, FirstName,LastName, Mail, MailVerified, PasswordHash, StatusGroup,privileges, Enabled

    private Long Id;
    private String firstName;
    private String lastName;
    private String mail;

    public User(String firstName, String lastName, String mail) {
        //Id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
    }

    public Long getId() {
        return Id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMail() {
        return mail;
    }
}
