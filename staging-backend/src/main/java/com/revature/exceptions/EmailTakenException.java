package com.revature.exceptions;

/**
 * This exception is used when a user tries to create a new user account using
 * an email that is already in use by an existing account
 */
public class EmailTakenException extends Exception{
    public EmailTakenException(String msg){
        super(msg);
    }
}
