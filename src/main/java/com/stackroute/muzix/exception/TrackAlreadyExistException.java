package com.stackroute.muzix.exception;

// class for creating custom exception if the track already exists
public class TrackAlreadyExistException extends Exception {

    // property of trackAlreadyExistException
    private String message;

    // no-parameter constructor
    public TrackAlreadyExistException(){}

    // parameterised constructor
    public TrackAlreadyExistException(String message){
        super(message);
        this.message=message;
    }

}
