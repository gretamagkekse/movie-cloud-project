package de.tu_berlin.proscience.mca.team_yellow.team_yellow.service;

import de.tu_berlin.proscience.mca.team_yellow.team_yellow.model.BlogUser;
import de.tu_berlin.proscience.mca.team_yellow.team_yellow.repository.UserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<BlogUser> userOptional = userRepository.findByUserName(username);
        if(userOptional.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        BlogUser user = userOptional.get();
        return new User(user.getUserName(), user.getPasswordHash(), Set.of(new SimpleGrantedAuthority("ROLE_USER")));
    }


}
