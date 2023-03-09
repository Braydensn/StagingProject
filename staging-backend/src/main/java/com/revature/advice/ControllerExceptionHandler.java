package com.revature.advice;

import com.revature.exceptions.EmailTakenException;
import com.revature.exceptions.InvalidLoginException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * This class handles exceptions thrown from the controller layer using AOP
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Handles exceptions related to unsuccessful login attempts in the controller layer
     * @param ex The exception thrown
     * @return A response entity to be sent back to the client
     */
    @ExceptionHandler(InvalidLoginException.class)
    public ResponseEntity handleInvalidLoginException(InvalidLoginException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    /**
     * Handles exceptions related to invalid registration attempts due to a duplicate email address
     * @param ex The exception thrown
     * @return A response entity to be sent back to the client
     */
    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity handleEmailTakenException(EmailTakenException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
