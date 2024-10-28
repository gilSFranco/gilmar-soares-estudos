package com.compasso.parking_management_spring_boot.web.exception;

import com.compasso.parking_management_spring_boot.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice  // ouvinte das exceções lançadas pela controller
public class GlobalExceptionHandler {

    @ExceptionHandler(CategoryNotValidException.class)
    public ResponseEntity<ErrorMessage> categoryNotValidException(CategoryNotValidException ex,
                                                                        HttpServletRequest request) {
        log.error("Error on API - {}", ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.BAD_REQUEST, // status 400
                        ex.getMessage()
                ));
    }

    // exceção lançada pela validação dos campos incorretos do DTO
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorMessage> methodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                        HttpServletRequest request,
                                                                        BindingResult result) {
        log.error("Error on API - {}", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(
                        request,
                        HttpStatus.BAD_REQUEST,
                        "Campo(s) inválido(s)",
                        result));
    }

    @ExceptionHandler(ValidationEnumException.class)
    public ResponseEntity<ErrorMessage> validationEnumException(ValidationEnumException ex,
                                                                        HttpServletRequest request) {

        log.error("Error on API - {}", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)  // need to find the invalidException and customize a related exception
    public ResponseEntity<ErrorMessage> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                     HttpServletRequest request) {

        log.error("Error on API - {}", ex.getMessage());  // exemplo de resposta:

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)  // Status 400
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    // , InvalidVehicleType.class
    @ExceptionHandler({PlateUniqueViolationException.class})
    public ResponseEntity<ErrorMessage> uniqueViolationException(RuntimeException ex, HttpServletRequest request) {

        log.error("Error on API - ", ex);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)  // Status 409
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler({NoVacanciesStartedException.class})
    public ResponseEntity<ErrorMessage> noVacanciesStartedException(RuntimeException ex, HttpServletRequest request) {

        log.error("Error on API - ", ex);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)  // Status 409
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    // NoSuchAvailableVacancies
    @ExceptionHandler({NoSuchAvailableVacancies.class})
    public ResponseEntity<ErrorMessage> noSuchAvailableVacancies(RuntimeException ex, HttpServletRequest request) {

        log.error("Error on API - ", ex);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)  // Status 409
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    // ConflictWithTypesException
    @ExceptionHandler({ConflictWithTypesException.class})
    public ResponseEntity<ErrorMessage> conflictWithTypesException(RuntimeException ex, HttpServletRequest request) {

        log.error("Error on API - ", ex);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    // IvalidCancel
    @ExceptionHandler({IvalidCancelException.class})
    public ResponseEntity<ErrorMessage> ivalidCancelException(RuntimeException ex, HttpServletRequest request) {

        log.error("Error on API - ", ex);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    // VehicleAlreadyParked
    @ExceptionHandler({VehicleAlreadyParkedException.class})
    public ResponseEntity<ErrorMessage> vehicleAlreadyParkedException(RuntimeException ex, HttpServletRequest request) {

        log.error("Error on API - ", ex);

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.CONFLICT, ex.getMessage()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(RuntimeException ex, HttpServletRequest request) {  // o HttpServletRequest é o payload da requisição?

        log.error("Error on API - ", ex);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)  // 404
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    // MethodArgumentTypeMismatchException
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)  // need to find the invalidException and customize a related exception
    public ResponseEntity<ErrorMessage> cannotResolveArgumentTypeException(MethodArgumentTypeMismatchException ex,
                                                                     HttpServletRequest request) {

        log.error("Error on API - {}", ex.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)  // Status 400
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.BAD_REQUEST, String.format("Id '%s' not found", request.getRequestURI().split("/")[4])));
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ErrorMessage> methodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {

        log.error("Error on API - ", ex);  // error log for monitoring

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage()));
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<ErrorMessage> noResourceFoundException(NoResourceFoundException ex, HttpServletRequest request) {

        log.error("Error on API - ", ex);  // error log for monitoring

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new ErrorMessage(request, HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> internalServerErrorException(Exception ex, HttpServletRequest request) {
        ErrorMessage error = new ErrorMessage(
                request, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        log.error("Internal Server Error {} {} ", error, ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }
}