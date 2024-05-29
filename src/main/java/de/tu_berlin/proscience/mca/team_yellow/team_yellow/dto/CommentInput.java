package de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto;

public class CommentInput {

    private String content;
    private String author;


    public CommentInput(String content, String author) {
        this.content = content;
        this.author = author;

    }
    public String getContent() {
        return content;
    }
    public String getAuthor() {
        return author;
    }
}
