package com.revature.exceptions;

public class InvalidLoginException extends Exception{
    public InvalidLoginException(String msg){
        super(msg);
    }
}
