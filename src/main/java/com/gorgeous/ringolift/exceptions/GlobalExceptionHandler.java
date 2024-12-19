package com.gorgeous.ringolift.exceptions;

import com.gorgeous.ringolift.responses.ResponseObject;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// This class is used to handle exceptions globally
// This class is annotated with @RestControllerAdvice
// Using aspect oriented programming, this class will handle exceptions globally
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseObject> handleGeneralException(Exception exception) {
        String exceptionType = exception.getClass().getName(); // Fully qualified exception class name
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseObject.builder()
                        .message(exceptionType)
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .data(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ResponseObject> handleDataNotFoundException(
            DataNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ResponseObject.builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND)
                        .data(null)
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseObject> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        // Get all field error messages
        Map<String, String> errors = new HashMap<>();
        String message = "Data validation error";

        // Regular Expression
        String regex = "([a-z])([A-Z]+)";

        // Replacement string
        String replacement = "$1_$2";

        result.getFieldErrors()
                .forEach(fieldError -> errors.put(
                        fieldError.getField().replaceAll(regex, replacement).toLowerCase(),
                        fieldError.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseObject.builder()
                        .message(message)
                        .status(HttpStatus.BAD_REQUEST)
                        .data(errors)
                        .build());
    }

    @ExceptionHandler({ ValidationException.class, InvalidParamException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseObject> handleValidationException(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseObject.builder()
                        .message("Validation error")
                        .status(HttpStatus.BAD_REQUEST)
                        .data(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(PermissionDenyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseObject> handlePermissionDenyException(PermissionDenyException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ResponseObject.builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.BAD_REQUEST)
                        .data(null)
                        .build());
    }

    @ExceptionHandler(DuplicateDataException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ResponseObject> handleDuplicateDataException(DuplicateDataException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ResponseObject.builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.CONFLICT)
                        .data(null)
                        .build());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseObject> handleUnauthorizedException(UnauthorizedException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseObject.builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.UNAUTHORIZED)
                        .data(null)
                        .build());
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseObject> handleInvalidTokenException(InvalidTokenException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseObject.builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.UNAUTHORIZED)
                        .data(null)
                        .build());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ResponseObject> handleExpiredJwtException(ExpiredJwtException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseObject.builder()
                        .message("Token expired")
                        .status(HttpStatus.UNAUTHORIZED)
                        .data(null)
                        .build());
    }

}
