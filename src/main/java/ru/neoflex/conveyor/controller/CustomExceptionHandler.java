package ru.neoflex.conveyor.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.neoflex.conveyor.exception.RefusalException;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RefusalException.class)
    public ResponseEntity<Object> handleBadRequestEx(RefusalException ex){
        return new ResponseEntity<>(ex.getMessage(),ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBadRequestEx(MethodArgumentNotValidException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
