package de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleCommentRepository extends JpaRepository<Comment, Long> {
}
