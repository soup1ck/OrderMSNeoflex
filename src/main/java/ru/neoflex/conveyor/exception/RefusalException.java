package ru.neoflex.conveyor.exception;

import org.springframework.http.HttpStatus;

public class RefusalException extends ApiException {

    public RefusalException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
