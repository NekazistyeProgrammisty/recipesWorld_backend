package com.restmvc.foodboard.exception;

public class UserNotFoundedException extends Exception{

    public UserNotFoundedException(String message) {
        super(message);
    }
}
