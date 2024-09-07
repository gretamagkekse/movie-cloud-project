package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto.CommentInput;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Comment;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    // Add a new comment for a movie
    @PostMapping("/{movieId}")
    public Comment addComment(@PathVariable Long movieId, @RequestBody CommentInput commentInput) {
        // Only the content and rating will be passed; username is auto-set to 'Tanem'
        return commentService.addComment(movieId, commentInput.getContent(), commentInput.getRating());
    }

    // Get all comments for a specific movie
    @GetMapping("/{movieId}")
    public List<Comment> getComments(@PathVariable Long movieId) {
        return commentService.getCommentsByMovieId(movieId);
    }
}

