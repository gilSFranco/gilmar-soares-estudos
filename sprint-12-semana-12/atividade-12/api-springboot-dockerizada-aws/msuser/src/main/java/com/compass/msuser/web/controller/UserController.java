package com.compass.msuser.web.controller;

import com.compass.msuser.entities.User;
import com.compass.msuser.service.UserService;
import com.compass.msuser.web.dto.ResponseUserDTO;
import com.compass.msuser.web.dto.UpdatePasswordDTO;
import com.compass.msuser.web.dto.UserDTO;
import com.compass.msuser.web.dto.mapper.UserMapper;
import com.compass.msuser.web.dto.security.AccountCredentialsDTO;
import com.compass.msuser.web.dto.security.TokenDTO;
import com.compass.msuser.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(
        name = "Users",
        description = "User operations"
)
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "User registration",
            description = "Resource to create a new user.",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Resource created successfully.",
                            headers = @Header(
                                    name = HttpHeaders.LOCATION,
                                    description = "URL to access the created resource"
                            ),
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ResponseUserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Resource with invalid fields.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Username already in use.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<ResponseUserDTO> create(@RequestBody @Valid UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();

        return ResponseEntity.created(uri).body(UserMapper.toDto(createdUser));
    }

    @Operation(
            summary = "User sign in",
            description = "Resource to sign in a user.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User successfully signed in.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = TokenDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Resource with invalid fields.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    /*
                        Não sei ao certo se essa exceção ocorrerá de fato, mas ela pode ocorrer
                        devido ao uso do método findUserByUsername.
                     */
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "User credentials are incorrect.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )

            }
    )
    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid AccountCredentialsDTO accountCredentialsDTO) {
        TokenDTO token = userService.signInUser(accountCredentialsDTO);
        return ResponseEntity.ok(token);
    }

    @Operation(
            summary = "User password update",
            description = "Resource to update the password of an already registered user.",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Password updated successfully."
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Resource with invalid fields or wrong password.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    ),
                    /*
                        Sei que não é melhor maneira de retornar um erro,
                        mas a questão de tratar a expiração do token, eu não consegui fazer.
                        Por isso ele acaba retornando esse erro, e por algum motivo
                        ele acaba não retornando no formato de ErrorMessage.
                    */
                    @ApiResponse(
                            responseCode = "500",
                            description = "Authentication did not occur as expected.",
                            content = @Content(
                                    mediaType = "application/json;",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    )

            }
    )
    @PutMapping("/update-password")
    public ResponseEntity<Void> updatePassword(@RequestBody @Valid UpdatePasswordDTO updatePasswordDTO) {
        userService.updatePassword(updatePasswordDTO);
        return ResponseEntity.noContent().build();
    }
}
