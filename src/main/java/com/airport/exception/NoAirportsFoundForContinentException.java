package com.airport.exception;

public class NoAirportsFoundForContinentException extends RuntimeException {

    public NoAirportsFoundForContinentException(String msg) {
        super(msg);
    }
}
