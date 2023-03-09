package com.revature.exceptions;

/**
 * This exception is used when a login request is unsuccessful
 */
public class InvalidLoginException extends Exception{
    public InvalidLoginException(String msg){
        super(msg);
    }
}
