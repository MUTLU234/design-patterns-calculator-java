package com.learning.calculator.exceptions;

/**
 * Exception thrown when attempting to calculate square root of a negative
 * number.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class NegativeSquareRootException extends CalculatorException {

    /**
     * Constructs a new negative square root exception with the specified detail
     * message.
     * 
     * @param message the detail message
     */
    public NegativeSquareRootException(String message) {
        super(message);
    }
}
