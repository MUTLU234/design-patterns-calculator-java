package com.learning.calculator.operations.basic;

import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Subtraction operation implementation.
 * Performs subtraction of two numbers: a - b
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class Subtraction implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(Subtraction.class);

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing subtraction: {} - {}", a, b);
        double result = a - b;
        logger.debug("Subtraction result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "-";
    }

    @Override
    public String getName() {
        return "Subtraction";
    }
}
