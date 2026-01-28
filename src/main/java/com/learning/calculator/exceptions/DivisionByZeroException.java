package com.learning.calculator.exceptions;

/**
 * Exception thrown when attempting to divide by zero.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class DivisionByZeroException extends CalculatorException {

    /**
     * Constructs a new division by zero exception with the specified detail
     * message.
     * 
     * @param message the detail message
     */
    public DivisionByZeroException(String message) {
        super(message);
    }
}
