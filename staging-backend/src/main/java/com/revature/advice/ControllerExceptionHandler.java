package com.revature.advice;

import com.revature.exceptions.EmailTakenException;
import com.revature.exceptions.InvalidLoginException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity handleInvalidLoginException(InvalidLoginException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity handleEmailTakenException(EmailTakenException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
