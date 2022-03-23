package com.restmvc.foodboard.exception;

public class AlreadyExistException extends Exception{
    public AlreadyExistException(String message) {
        super(message);
    }
}
