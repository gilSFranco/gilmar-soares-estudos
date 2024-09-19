package com.compasso.demo_park_api.web.exception;

import com.compasso.demo_park_api.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(ClienteCpfNotFoundException.class)
    public ResponseEntity<ErrorMessage> clienteCpfNotFoundException(ClienteCpfNotFoundException e, HttpServletRequest request) {
        Object[] params = new Object[]{e.getRecurso(), e.getCpf()};
        String message = messageSource.getMessage("exception.clienteCpfNotFoundException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(ClienteIdNotFoundException.class)
    public ResponseEntity<ErrorMessage> clienteIdNotFoundException(ClienteIdNotFoundException e, HttpServletRequest request) {
        Object[] params = new Object[]{e.getRecurso(), e.getId()};
        String message = messageSource.getMessage("exception.clienteIdNotFoundException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(ReciboNotFoundException.class)
    public ResponseEntity<ErrorMessage> reciboNotFoundException(ReciboNotFoundException e, HttpServletRequest request) {
        Object[] params = new Object[]{e.getRecurso(), e.getRecibo()};
        String message = messageSource.getMessage("exception.reciboNotFoundException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {
        Object[] params = new Object[]{e.getRecurso(), e.getCodigo()};
        String message = messageSource.getMessage("exception.entityNotFoundException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler({CodigoUniqueViolationException.class})
    public ResponseEntity<ErrorMessage> codigoUniqueViolationException(CodigoUniqueViolationException e, HttpServletRequest request) {
        Object[] params = new Object[]{e.getRecurso(), e.getCodigo()};
        String message = messageSource.getMessage("exception.codigoUniqueViolationException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, message));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorMessage> accessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        log.error("Api Error - ", e);
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.FORBIDDEN, e.getMessage()));
    }

    @ExceptionHandler(VagaDisponivelException.class)
    public ResponseEntity<ErrorMessage> vagaDisponivelException(VagaDisponivelException e, HttpServletRequest request) {
        Object[] params = new Object[]{e.getRecurso()};
        String message = messageSource.getMessage("exception.vagaDisponivelException", params, request.getLocale());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, message));
    }

    @ExceptionHandler(
            {
                    UsernameUniqueViolationException.class,
                    CpfUniqueViolationException.class
            }
    )
    public ResponseEntity<ErrorMessage> uniqueViolationException(RuntimeException e, HttpServletRequest request) {
        log.error("Api Error - ", e);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, e.getMessage()));
    }

    @ExceptionHandler(PasswordInvalidException.class)
    public ResponseEntity<ErrorMessage> passwordInvalidException(RuntimeException e, HttpServletRequest request) {
        log.error("Api Error - ", e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request, BindingResult bindingResult) {
        log.error("Api Error - ", e);
        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.UNPROCESSABLE_ENTITY,
                        messageSource.getMessage("message.invalid.field", null, request.getLocale()),
                        bindingResult,
                        messageSource
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> internalServerErrorException(Exception e, HttpServletRequest request) {
        ErrorMessage error =
                new ErrorMessage(
                        request,
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
                );

        log.error("Internal Server Error {} | {} ", error, e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }
}
