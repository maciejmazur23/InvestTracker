package com.example.investanalizer.infrastructure.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBindException(BindException exception) {
        log.error("Bad value of field: [{}]",
                Optional.ofNullable(exception.getFieldError()).map(FieldError::getField).orElse(null)
        );
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleRuntimeException(RuntimeException exception) {
        log.error(Optional.ofNullable(exception.getMessage()).orElse("Something went wrong"));
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

}
