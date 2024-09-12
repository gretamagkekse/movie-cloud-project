package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto.CommentInput;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Comment;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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
    @SecurityRequirement(name = "BasicAuth")
    public Comment addComment(@PathVariable Long movieId, @RequestBody CommentInput commentInput, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        System.out.println("commentController"+ commentInput.getRatingActors());
        return commentService.addComment(commentInput, movieId, user.getUsername());
    }

    // Get all comments for a specific movie
    @GetMapping("/{movieId}")
    public List<Comment> getComments(@PathVariable Long movieId) {
        return commentService.getCommentsByMovieId(movieId);
    }

    @GetMapping("/user-comments/{username}")
    public List<Comment> getUserComments(@PathVariable String username) {
        return commentService.getCommentsByUserName(username);
    }

    @DeleteMapping("/user-comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        boolean isDeleted = commentService.deleteComment(id, userDetails.getUsername());

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // 403 if unauthorized or not found
        }

        return ResponseEntity.noContent().build();  // 204 if deleted
    }

}

