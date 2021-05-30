package com.airport.exception;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 */
public class NoAirportsFoundForContinentException extends RuntimeException {

    public NoAirportsFoundForContinentException(String msg) {
        super(msg);
    }
}
