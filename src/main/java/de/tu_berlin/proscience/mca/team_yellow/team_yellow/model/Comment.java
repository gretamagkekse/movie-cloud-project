package de.tu_berlin.proscience.mca.team_yellow.team_yellow.model;

public class Comment {
    //id,content,author, module_id
    private Long id;
    private String content;
    private String author;
    private Long module_id;
    private Long user_id;

    public Comment(Long id, String content, String author, Long module_id, Long user_id) {
        this.id = id;
        this.content = content;
        this.author = author;
        this.module_id = module_id;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public Long getModule_id() {
        return module_id;
    }
    public Long getUser_id() {
        return user_id;
    }
}
