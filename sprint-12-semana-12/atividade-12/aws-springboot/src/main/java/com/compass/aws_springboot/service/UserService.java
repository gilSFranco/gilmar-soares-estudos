package com.compass.aws_springboot.service;

import com.compass.aws_springboot.domain.model.ServiceStatus;
import com.compass.aws_springboot.infra.mqueue.SendingRabbitmqPublisher;
import com.compass.aws_springboot.web.dto.ResponseViaCepDTO;
import com.compass.aws_springboot.entities.User;
import com.compass.aws_springboot.infra.clients.ViaCepResourceClient;
import com.compass.aws_springboot.repository.UserRepository;
import com.compass.aws_springboot.web.dto.UpdatePasswordDTO;
import com.compass.aws_springboot.web.dto.UserDTO;
import com.compass.aws_springboot.web.dto.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ViaCepResourceClient viaCepResourceClient;
    private final SendingRabbitmqPublisher sendingRabbitmq;

    @Transactional
    public User createUser(UserDTO userDTO) {
        ResponseViaCepDTO response = findAddressByZipCode(userDTO.getCep());
        User newUser = UserMapper.toUser(userDTO);

        newUser.setZipCode(response.getZipCode());
        newUser.setStreet(response.getStreet());
        newUser.setComplement(response.getComplement());
        newUser.setNeighborhood(response.getNeighborhood());
        newUser.setCity(response.getCity());
        newUser.setState(response.getState());

        ServiceStatus serviceStatus = new ServiceStatus();

        serviceStatus.setUsername(newUser.getUsername());
        serviceStatus.setOperation("CREATE");

        sendingStatusRabbitmq(serviceStatus);

        return userRepository.save(newUser);
    }

    public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        User user = findUserByUsername(updatePasswordDTO.getUsername());

        if(!user.getPassword().equalsIgnoreCase(updatePasswordDTO.getOldPassword())) {
            throw new RuntimeException("Incorrect password. Try again!");
        }

        ServiceStatus serviceStatus = new ServiceStatus();

        serviceStatus.setUsername(user.getUsername());
        serviceStatus.setOperation("UPDATE");

        sendingStatusRabbitmq(serviceStatus);

        user.setPassword(updatePasswordDTO.getNewPassword());
        userRepository.save(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException(
                        String.format("User with username '%s' not found.", username)
                )
        );
    }

    public ResponseViaCepDTO findAddressByZipCode(String zipCode) {
        return viaCepResourceClient.getZipCodeInformation(zipCode);
    }

    public void sendingStatusRabbitmq(ServiceStatus data) {
        try {
            sendingRabbitmq.showStatus(data);
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
