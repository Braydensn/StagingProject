package com.revature.exceptions;

public class EmailTakenException extends Exception{
    public EmailTakenException(String msg){
        super(msg);
    }
}
