package com.stackroute.muzix.exception;

public class TrackAlreadyExistException extends Exception {

    // property of trackAlraedyExistException
    private String message;

    // no-parameter constructor
    public TrackAlreadyExistException(){}

    // parameterised constructor
    public TrackAlreadyExistException(String message){
        super(message);
        this.message=message;
    }

}
