package com.learning.calculator.operations.advanced;

import com.learning.calculator.exceptions.DivisionByZeroException;
import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Modulus operation implementation.
 * Calculates the remainder of a divided by b: a % b
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class Modulus implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(Modulus.class);
    private static final double EPSILON = 1e-10;

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing modulus: {} % {}", a, b);

        if (Math.abs(b) < EPSILON) {
            logger.error("Modulus by zero attempted: {} % {}", a, b);
            throw new DivisionByZeroException(
                    String.format("Cannot calculate modulus with divisor zero: %.2f %% 0", a));
        }

        double result = a % b;
        logger.debug("Modulus result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "%";
    }

    @Override
    public String getName() {
        return "Modulus";
    }
}
