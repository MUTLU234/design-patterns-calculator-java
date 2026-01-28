package com.learning.calculator.exceptions;

/**
 * Base exception class for all calculator-related exceptions.
 * Extends RuntimeException to avoid checked exception handling complexity.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class CalculatorException extends RuntimeException {

    /**
     * Constructs a new calculator exception with the specified detail message.
     * 
     * @param message the detail message
     */
    public CalculatorException(String message) {
        super(message);
    }

    /**
     * Constructs a new calculator exception with the specified detail message and
     * cause.
     * 
     * @param message the detail message
     * @param cause   the cause of this exception
     */
    public CalculatorException(String message, Throwable cause) {
        super(message, cause);
    }
}
