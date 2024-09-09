package de.tu_berlin.proscience.mca.team_yellow.team_yellow.controller;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto.CommentInput;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Comment;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.service.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class CommentController {

    private CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }



    // POST A COMMENT
    @PostMapping(path="/comments")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityRequirement(name = "BasicAuth")
    public Comment addComment(@RequestBody CommentInput commentInput, Authentication authentication) {
        if (commentInput.getContent().isBlank() || commentInput.getContent() == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        User user = (User) authentication.getPrincipal();
        return commentService.addComment(commentInput, user.getUsername());
    }

    // GET ALL COMMENTS
    @GetMapping(path = "/comments")
    public List<Comment> getComments() {
        return commentService.getAllComments();
    }

//    @GetMapping(path="/comments/user/{id}")
//    public List<Comment> getAllCommentsByUserId(@PathVariable Long id) {
//        return commentService.getAllCommentsByUserId(id);
//    }
//    @GetMapping(path="/comments/{id}")
//    public List<Comment> getAllCommentsByModule(@PathVariable Long id) {
//        return commentService.getAllCommentsByModule(id);
//    }

}
