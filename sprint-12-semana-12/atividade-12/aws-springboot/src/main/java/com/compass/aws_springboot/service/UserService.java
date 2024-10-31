package com.compass.aws_springboot.service;

import com.compass.aws_springboot.domain.model.ServiceStatus;
import com.compass.aws_springboot.exceptions.AuthenticationNotCompleteException;
import com.compass.aws_springboot.exceptions.InvalidPasswordException;
import com.compass.aws_springboot.exceptions.MessageNotSendException;
import com.compass.aws_springboot.exceptions.UserNotFoundException;
import com.compass.aws_springboot.infra.mqueue.SendingRabbitmqPublisher;
import com.compass.aws_springboot.security.jwt.JwtTokenProvider;
import com.compass.aws_springboot.web.dto.ResponseViaCepDTO;
import com.compass.aws_springboot.entities.User;
import com.compass.aws_springboot.infra.clients.ViaCepResourceClient;
import com.compass.aws_springboot.repository.UserRepository;
import com.compass.aws_springboot.web.dto.UpdatePasswordDTO;
import com.compass.aws_springboot.web.dto.UserDTO;
import com.compass.aws_springboot.web.dto.mapper.UserMapper;
import com.compass.aws_springboot.web.dto.security.AccountCredentialsDTO;
import com.compass.aws_springboot.web.dto.security.TokenDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final ViaCepResourceClient viaCepResourceClient;
    private final SendingRabbitmqPublisher sendingRabbitmq;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

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

        newUser.setPassword(encryptPassword(userDTO.getPassword()));

        sendStatusToMsNotify(newUser.getUsername(), "CREATE");

        return userRepository.save(newUser);
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public void updatePassword(UpdatePasswordDTO updatePasswordDTO) {
        User user = findUserByUsername(updatePasswordDTO.getUsername());

        if(!passwordEncoder.matches(updatePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new InvalidPasswordException("Incorrect password. Try again!");
        }

        sendStatusToMsNotify(user.getUsername(), "UPDATE");

        user.setPassword(encryptPassword(updatePasswordDTO.getNewPassword()));
        userRepository.save(user);
    }

    public void sendStatusToMsNotify(String username, String operation) {
        ServiceStatus serviceStatus = new ServiceStatus();

        serviceStatus.setUsername(username);
        serviceStatus.setOperation(operation);

        sendingStatusRabbitmq(serviceStatus);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UserNotFoundException(
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
            throw new MessageNotSendException(e.getMessage());
        }
    }

    public TokenDTO signInUser(AccountCredentialsDTO accountCredentialsDTO) {
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            accountCredentialsDTO.getUsername(),
                            accountCredentialsDTO.getPassword()
                    )
            );

            User user = findUserByUsername(accountCredentialsDTO.getUsername());

            TokenDTO response = new TokenDTO();

            if(user != null) {
                response = jwtTokenProvider.createAccessToken(accountCredentialsDTO.getUsername());
            } else {
                throw new UserNotFoundException(
                        String.format("User with username '%s' not found.", accountCredentialsDTO.getUsername())
                );
            }

            return response;
        } catch(Exception e) {
            throw new AuthenticationNotCompleteException(e.getMessage());
        }
    }
}
