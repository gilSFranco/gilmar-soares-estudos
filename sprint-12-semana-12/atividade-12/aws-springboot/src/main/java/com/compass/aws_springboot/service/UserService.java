package com.compass.aws_springboot.service;

import com.compass.aws_springboot.entities.User;
import com.compass.aws_springboot.repository.UserRepository;
import com.compass.aws_springboot.web.dto.UserDTO;
import com.compass.aws_springboot.web.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserDTO userDTO) {
        User newUser = UserMapper.toUser(userDTO);

        newUser.setZipCode("12345678");
        newUser.setStreet("Example Street");
        newUser.setComplement("Apt 01");
        newUser.setNeighborhood("Example Neighborhood");
        newUser.setCity("Example City");
        newUser.setState("SP");

        return userRepository.save(newUser);
    }
}
