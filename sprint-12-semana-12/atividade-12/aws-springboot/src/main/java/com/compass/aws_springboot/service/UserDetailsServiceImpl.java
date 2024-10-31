package com.compass.aws_springboot.service;

import com.compass.aws_springboot.entities.User;
import com.compass.aws_springboot.exceptions.UserNotFoundException;
import com.compass.aws_springboot.repository.UserRepository;
import com.compass.aws_springboot.security.UserDetailsImpl;
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
