package com.compass.msuser.service;

import com.compass.msuser.entities.User;
import com.compass.msuser.exceptions.UserNotFoundException;
import com.compass.msuser.repository.UserRepository;
import com.compass.msuser.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(
                        String.format("User with username '%s' not found.", username)
                )
        );

        return new UserDetailsImpl(user);
    }
}
