package com.stackroute.muzix.exception;

public class TrackNotFoundException extends Exception {

    // property of trackNotFoundException
    private String message;

    // no-parameter constructor
    public TrackNotFoundException() {
    }

    // parameterised constructor
    public TrackNotFoundException(String message) {
        super(message);
        this.message=message;
    }
}
