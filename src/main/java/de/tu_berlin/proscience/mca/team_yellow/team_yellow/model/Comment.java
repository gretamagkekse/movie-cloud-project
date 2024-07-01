package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Comment {
    //id,content,author, module_id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 4096)
    private String content;

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private BlogUser author;
//    private Long module_id;
//    private Long user_id;


    public Comment() {
    }



    public Comment( String content, BlogUser author) {
        this.content = content;
        this.author = author;
//        this.module_id = module_id;
//        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public BlogUser getAuthor() {
        return author;
    }
//
//    public Long getModule_id() {
//        return module_id;
//    }
//    public Long getUser_id() {
//        return user_id;
//    }
}
