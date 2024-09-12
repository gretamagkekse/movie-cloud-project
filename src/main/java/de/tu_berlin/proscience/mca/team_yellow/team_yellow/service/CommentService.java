package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.dto.CommentInput;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Comment;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


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

    // retrieve all comments for a specific user
    public List<Comment> getCommentsByUserName(String userName) {
        return commentRepository.findByUserName(userName);
    }

    public boolean deleteComment(Long commentId, String username) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);

        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();

            // does the logged-in user own the comment
            if (!comment.getUser().getUserName().equals(username)) {
                return false;  // Unauthorized
            }

            // Delete the comment
            commentRepository.deleteById(commentId);
            return true;  // Successfully deleted
        }

        return false;  // Comment not found
    }
}







