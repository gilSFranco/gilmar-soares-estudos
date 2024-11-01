package com.compass.msuser.service;

import com.compass.msuser.domain.model.ServiceStatus;
import com.compass.msuser.exceptions.*;
import com.compass.msuser.infra.mqueue.SendingRabbitmqPublisher;
import com.compass.msuser.security.jwt.JwtTokenProvider;
import com.compass.msuser.web.dto.ResponseViaCepDTO;
import com.compass.msuser.entities.User;
import com.compass.msuser.infra.clients.ViaCepResourceClient;
import com.compass.msuser.repository.UserRepository;
import com.compass.msuser.web.dto.UpdatePasswordDTO;
import com.compass.msuser.web.dto.UserDTO;
import com.compass.msuser.web.dto.mapper.UserMapper;
import com.compass.msuser.web.dto.security.AccountCredentialsDTO;
import com.compass.msuser.web.dto.security.TokenDTO;
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
        // Tratativa de erro, caso a senha não senha criptografada com sucesso
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
        // Acredito que aqui tambem deveria ter uma tratativa de erro
        // caso a requisição der errado
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
            throw new AuthenticationNotCompleteException("Your credentials are incorrect.");
        }
    }
}
