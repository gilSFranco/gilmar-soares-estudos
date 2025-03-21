package com.compasso.demo_park_api.web.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Getter
@ToString
public class ErrorMessage {
    private String path;
    private String method;
    private int status;
    private String statusText;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;

    public ErrorMessage() {
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult bindingResult) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
        addErrors(bindingResult);
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message, BindingResult bindingResult, MessageSource messageSource) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
        addErrors(bindingResult, messageSource, request.getLocale());
    }

    private void addErrors(BindingResult bindingResult, MessageSource messageSource, Locale locale) {
        this.errors = new HashMap<>();
        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            String code = fieldError.getCodes()[0];
            String message = messageSource.getMessage(code, fieldError.getArguments(),  locale);
            this.errors.put(fieldError.getField(), message);
        }
    }

    private void addErrors(BindingResult bindingResult) {
        this.errors = new HashMap<>();
        for(FieldError fieldError : bindingResult.getFieldErrors()) {
            this.errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }
}
