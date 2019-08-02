package com.stackroute.muzix.exception;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class GlobalExceptionHandler {

    // handle TrackNotFoundException
    @ExceptionHandler(TrackNotFoundException.class)
    public ResponseEntity<VndErrors> notFoundException(final TrackNotFoundException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }

    // return response
    private ResponseEntity < VndErrors > error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity < > (new VndErrors(logRef, message), httpStatus);
    }

    // handle TrackAlreadyExistException
    @ExceptionHandler(TrackAlreadyExistException.class) public ResponseEntity < VndErrors > alreadyExistException
    (final TrackAlreadyExistException e) {
        return error(e, HttpStatus.CONFLICT, e.getLocalizedMessage());
    }
}

