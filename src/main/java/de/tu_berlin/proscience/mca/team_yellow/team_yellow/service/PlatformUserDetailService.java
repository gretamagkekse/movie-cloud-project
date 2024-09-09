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

    public PlatformUserDetailService(PlatformUserRepository platformUserRepository) {
        this.platformUserRepository = platformUserRepository;
    }

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
