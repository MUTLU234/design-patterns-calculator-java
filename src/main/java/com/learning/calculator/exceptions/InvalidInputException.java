package com.learning.calculator.exceptions;

/**
 * Exception thrown when invalid input is provided.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class InvalidInputException extends CalculatorException {

    /**
     * Constructs a new invalid input exception with the specified detail message.
     * 
     * @param message the detail message
     */
    public InvalidInputException(String message) {
        super(message);
    }
}
