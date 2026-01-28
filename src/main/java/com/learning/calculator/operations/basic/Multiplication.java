package com.learning.calculator.operations.basic;

import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Multiplication operation implementation.
 * Performs multiplication of two numbers: a * b
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class Multiplication implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(Multiplication.class);

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing multiplication: {} * {}", a, b);
        double result = a * b;
        logger.debug("Multiplication result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    @Override
    public String getName() {
        return "Multiplication";
    }
}
