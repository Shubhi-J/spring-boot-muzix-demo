package com.stackroute.muzix.exception;

public class TrackNotFoundExeption extends Exception {

    // property of trackNotFoundException
    private String message;

    // no-parameter constructor
    public TrackNotFoundExeption() {
    }

    // parameterised constructor
    public TrackNotFoundExeption(String message) {
        super(message);
        this.message=message;
    }
}
