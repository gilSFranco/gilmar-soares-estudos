package com.compass.aws_springboot.web.exception;

import com.compass.aws_springboot.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidJwtAuthenticationException .class)
    public ResponseEntity<ErrorMessage> handleInvalidJwtAuthenticationExceptions(Exception ex, HttpServletRequest request) {
        log.error("Error on API - {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.FORBIDDEN,
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorMessage> handleInvalidPasswordException(Exception ex, HttpServletRequest request) {
        log.error("Error on API - {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(Exception ex, HttpServletRequest request) {
        log.error("Error on API - {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.NOT_FOUND,
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(MessageNotSendException.class)
    public ResponseEntity<ErrorMessage> handleMessageNotSendException(Exception ex, HttpServletRequest request) {
        log.error("Error on API - {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage()
                ));
    }

    @ExceptionHandler(AuthenticationNotCompleteException.class)
    public ResponseEntity<ErrorMessage> handleAuthenticationNotCompleteException(Exception ex, HttpServletRequest request) {
        log.error("Error on API - {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage()
                ));
    }
}
