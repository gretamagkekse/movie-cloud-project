package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.PlatformUser;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.PlatformUserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class PlatformUserDetailService implements UserDetailsService {

    private final PlatformUserRepository platformUserRepository;


    /**
     * Service class that provides user authentication details for security purposes.
     * Implements the UserDetailsService for getting user-specific data.
     */
    public PlatformUserDetailService(PlatformUserRepository platformUserRepository) {
        this.platformUserRepository = platformUserRepository;
    }

    /**
     * Loads the user by their username for auth.
     *
     * @param username the username of the user.
     * @return UserDetails containing the user's credentials and authorities.
     * @throws UsernameNotFoundException if the user is not found in the repository.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<PlatformUser> userOptional = platformUserRepository.findByUserName(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        PlatformUser user = userOptional.get();
        return new User(user.getUserName(), user.getPasswordHash(), Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    }


}
