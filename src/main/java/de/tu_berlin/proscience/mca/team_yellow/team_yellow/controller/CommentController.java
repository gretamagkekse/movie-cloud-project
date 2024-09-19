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


/**
 * Controller class for managing comments on movies.
 * Provides endpoints to add, retrieve, and delete comments.
 */
@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Adds a new comment for a specific movie.
     *
     * @param movieId        the ID of the movie to add a comment for.
     * @param commentInput   the input data for the comment (ratings and text).
     * @param authentication the authentication object containing the logged-in user's details.
     * @return the added Comment object.
     */
    @PostMapping("/{movieId}")
    @SecurityRequirement(name = "BasicAuth")
    public Comment addComment(@PathVariable Long movieId, @RequestBody CommentInput commentInput, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        System.out.println("commentController" + commentInput.getRatingActors());
        return commentService.addComment(commentInput, movieId, user.getUsername());
    }

    /**
     * Retrieves all comments for a specific movie.
     *
     * @param movieId the ID of the movie.
     * @return a list of Comment objects for the specified movie.
     */
    @GetMapping("/{movieId}")
    public List<Comment> getComments(@PathVariable Long movieId) {
        return commentService.getCommentsByMovieId(movieId);
    }

    /**
     * Retrieves all comments made by a specific user.
     *
     * @param username the username of the user.
     * @return a list of Comment objects made by the specified user.
     */
    @GetMapping("/user-comments/{username}")
    public List<Comment> getUserComments(@PathVariable String username) {
        return commentService.getCommentsByUserName(username);
    }

    /**
     * Deletes a comment made by the currently authenticated user.
     *
     * @param id          the ID of the comment to delete.
     * @param userDetails the authenticated user's details.
     * @return a ResponseEntity indicating the result of the deletion.
     */
    @DeleteMapping("/user-comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable("id") Long id, @AuthenticationPrincipal UserDetails userDetails) {
        boolean isDeleted = commentService.deleteComment(id, userDetails.getUsername());

        if (!isDeleted) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // 403 if unauthorized or not found
        }

        return ResponseEntity.noContent().build();  // 204 if deleted
    }

}

