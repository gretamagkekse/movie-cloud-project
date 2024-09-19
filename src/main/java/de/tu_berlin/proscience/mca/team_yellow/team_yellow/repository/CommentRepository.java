package de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing comments in the database.
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Finds all comments by the movie ID.
     *
     * @param movieId the ID of the movie.
     * @return a list of comments for the specified movie.
     */
    List<Comment> findByMovieId(Long movieId);

    /**
     * Find all comments made by a specific user.
     *
     * @param userName the username of the user.
     * @return a list of comments made by the specified user.
     */
    @Query("SELECT c FROM Comment c WHERE c.user.userName = :userName")
    List<Comment> findByUserName(@Param("userName") String userName);

    /**
     * Delete comment by its ID.
     *
     * @param commentId the ID of the comment to delete.
     */
    void deleteById(Long commentId);

}
