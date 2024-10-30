package com.compass.aws_springboot.web.exception;

import com.compass.aws_springboot.exceptions.InvalidJwtAuthenticationException;
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
}
