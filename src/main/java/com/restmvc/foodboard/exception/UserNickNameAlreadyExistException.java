package com.restmvc.foodboard.exception;

public class UserNickNameAlreadyExistException extends Exception{
    public UserNickNameAlreadyExistException(String message) {
        super(message);
    }
}
