package com.example.angular_spring.exceptions;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String message, Throwable causa){
        super(message, causa);
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
