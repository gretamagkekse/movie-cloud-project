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

    /**
     * Service class responsible for managing comments on movies.
     * It interacts with the CommentRepository and PlatformUserService.
     */
    @Autowired
    public CommentService(CommentRepository commentRepository, PlatformUserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    /**
     * Adds a new comment for a specific movie.
     *
     * @param commentInput the input data for the comment.
     * @param movieId      the ID of the movie being commented on.
     * @param userName     the username of the user adding the comment.
     * @return the saved Comment.
     */
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


    /**
     * Retrieves all comments for a specific movie.
     *
     * @param movieId the ID of the movie.
     * @return a list of comments for the movie.
     */
    public List<Comment> getCommentsByMovieId(Long movieId) {
        return commentRepository.findByMovieId(movieId);
    }

    /**
     * Retrieves all comments made by a specific user.
     *
     * @param userName the username of the user.
     * @return a list of comments made by the user.
     */
    public List<Comment> getCommentsByUserName(String userName) {
        return commentRepository.findByUserName(userName);
    }

    /**
     * Deletes a comment if it belongs to the logged-in user.
     *
     * @param commentId the ID of the comment to delete.
     * @param username  the username of the user attempting to delete the comment.
     * @return true if the comment was successfully deleted, false otherwise.
     */
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







