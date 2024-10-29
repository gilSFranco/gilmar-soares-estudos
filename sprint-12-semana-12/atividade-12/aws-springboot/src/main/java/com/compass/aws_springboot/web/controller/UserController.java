package com.compass.aws_springboot.web.controller;

import com.compass.aws_springboot.entities.User;
import com.compass.aws_springboot.service.UserService;
import com.compass.aws_springboot.web.dto.ResponseUserDTO;
import com.compass.aws_springboot.web.dto.UpdatePasswordDTO;
import com.compass.aws_springboot.web.dto.UserDTO;
import com.compass.aws_springboot.web.dto.mapper.UserMapper;
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

    @PutMapping("/update-password")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UpdatePasswordDTO updatePasswordDTO) {
        userService.updatePassword(updatePasswordDTO);
        return ResponseEntity.noContent().build();
    }
}
