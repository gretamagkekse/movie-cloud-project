package de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<BlogUser, Long> {

    Optional<BlogUser> findByUserName(String nameUser);
}
