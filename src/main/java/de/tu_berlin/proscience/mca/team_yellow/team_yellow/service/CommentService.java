package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

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
    private final PlatformUserRepository blogUserRepository;  // Add user repository

    @Autowired
    public CommentService(CommentRepository commentRepository, PlatformUserRepository blogUserRepository) {
        this.commentRepository = commentRepository;
        this.blogUserRepository = blogUserRepository;
    }

    // Add a new comment for a specific movie (all comments will be from Tanem)
    public Comment addComment(Long movieId, String content, Double rating) {
        // Fetch the Tanem user from the database
        PlatformUser tanemUser = blogUserRepository.findByUserName("Tanem")
                .orElseThrow(() -> new RuntimeException("User 'Tanem' not found"));

        // Create and save the comment with Tanem as the author
        Comment comment = new Comment(movieId, tanemUser, content, rating);
        return commentRepository.save(comment);
    }

    // Retrieve all comments for a specific movie
    public List<Comment> getCommentsByMovieId(Long movieId) {
        return commentRepository.findByMovieId(movieId);
    }
}

