package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto.CommentInput;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Comment;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.PlatformUser;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.CommentRepository;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.PlatformUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private PlatformUserService userService;

    @Autowired
    public CommentService(CommentRepository commentRepository, PlatformUserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    // Add a new comment for a specific movie
    public Comment addComment(CommentInput commentInput, Long movieId, String userName) {

        // Create and save the comment
        Comment comment = new Comment(
                commentInput.getRatingActors(),
                commentInput.getRatingStory(),
                commentInput.getRatingVisuals(),
                commentInput.getComment(),
                movieId,
                userService.getCurrentUser(userName).get());
        return commentRepository.save(comment);
    }

    // Retrieve all comments for a specific movie
    public List<Comment> getCommentsByMovieId(Long movieId) {
        return commentRepository.findByMovieId(movieId);
    }
}

