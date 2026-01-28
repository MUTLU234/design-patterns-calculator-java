package com.learning.calculator.operations.basic;

import com.learning.calculator.exceptions.DivisionByZeroException;
import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Division operation implementation.
 * Performs division of two numbers: a / b
 * Includes validation to prevent division by zero.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class Division implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(Division.class);
    private static final double EPSILON = 1e-10;

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing division: {} / {}", a, b);

        if (Math.abs(b) < EPSILON) {
            logger.error("Division by zero attempted: {} / {}", a, b);
            throw new DivisionByZeroException(
                    String.format("Cannot divide %.2f by zero", a));
        }

        double result = a / b;
        logger.debug("Division result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "/";
    }

    @Override
    public String getName() {
        return "Division";
    }
}
