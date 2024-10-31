package com.compass.msuser.web.controller;

import com.compass.msuser.entities.User;
import com.compass.msuser.service.UserService;
import com.compass.msuser.web.dto.ResponseUserDTO;
import com.compass.msuser.web.dto.UpdatePasswordDTO;
import com.compass.msuser.web.dto.UserDTO;
import com.compass.msuser.web.dto.mapper.UserMapper;
import com.compass.msuser.web.dto.security.AccountCredentialsDTO;
import com.compass.msuser.web.dto.security.TokenDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseUserDTO> create(@RequestBody @Valid UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();

        return ResponseEntity.created(uri).body(UserMapper.toDto(createdUser));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid AccountCredentialsDTO accountCredentialsDTO) {
        TokenDTO token = userService.signInUser(accountCredentialsDTO);
        return ResponseEntity.ok(token);
    }

    @PutMapping("/update-password")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UpdatePasswordDTO updatePasswordDTO) {
        userService.updatePassword(updatePasswordDTO);
        return ResponseEntity.noContent().build();
    }
}
