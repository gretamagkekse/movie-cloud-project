package de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Find all comments by movieId
    List<Comment> findByMovieId(Long movieId);

    @Query("SELECT c FROM Comment c WHERE c.user.userName = :userName")
    List<Comment> findByUserName(@Param("userName") String userName);

    void deleteById(Long CommentId);



}
