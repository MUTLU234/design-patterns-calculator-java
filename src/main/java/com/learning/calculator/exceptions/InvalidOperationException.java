package com.learning.calculator.exceptions;

/**
 * Exception thrown when an invalid operation is requested.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class InvalidOperationException extends CalculatorException {

    /**
     * Constructs a new invalid operation exception with the specified detail
     * message.
     * 
     * @param message the detail message
     */
    public InvalidOperationException(String message) {
        super(message);
    }
}
