package com.airport.exception;

public class NoHeliportFoundException extends RuntimeException {

    public NoHeliportFoundException(String msg) {
        super(msg);
    }
}
