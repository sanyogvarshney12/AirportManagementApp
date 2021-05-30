package com.airport.exception;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 */
public class NoHeliportFoundException extends RuntimeException {

    public NoHeliportFoundException(String msg) {
        super(msg);
    }
}
