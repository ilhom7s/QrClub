package com.project.qrclub.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UuidNotFoundException.class)
    public ResponseEntity<String> handleUuidNotFound(UuidNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }
    @ExceptionHandler(UserAlreadryInBaseException.class)
    public ResponseEntity<String> handleUserAlreadyInBase(UserAlreadryInBaseException message){
        return  ResponseEntity.
                status(HttpStatus.BAD_REQUEST)
                .body(message.getMessage());
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException message){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(message.toString());
    }
}
