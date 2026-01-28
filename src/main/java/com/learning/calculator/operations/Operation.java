package com.learning.calculator.operations;

/**
 * Interface for all calculator operations following the Strategy Pattern.
 * This design allows for easy extension of new operations without modifying existing code.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public interface Operation {
    /**
     * Executes the mathematical operation on two operands.
     * 
     * @param a First operand
     * @param b Second operand
     * @return Result of the operation
     * @throws ArithmeticException if the operation is mathematically invalid
     */
    double execute(double a, double b);
    
    /**
     * Gets the symbol representing this operation.
     * 
     * @return Operation symbol (e.g., "+", "-", "*", "/")
     */
    String getSymbol();
    
    /**
     * Gets the human-readable name of this operation.
     * 
     * @return Operation name (e.g., "Addition", "Subtraction")
     */
    String getName();
    
    /**
     * Gets a description of what this operation does.
     * 
     * @return Operation description
     */
    default String getDescription() {
        return String.format("%s (%s)", getName(), getSymbol());
    }
}
