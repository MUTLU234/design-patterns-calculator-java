package com.learning.calculator.operations.advanced;

import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Percentage operation implementation.
 * Calculates b percent of a: (a * b) / 100
 * For example: "What is 20% of 150?" -> execute(150, 20) = 30
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class Percentage implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(Percentage.class);

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing percentage: {}% of {}", b, a);
        double result = (a * b) / 100.0;
        logger.debug("Percentage result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "%";
    }

    @Override
    public String getName() {
        return "Percentage";
    }

    @Override
    public String getDescription() {
        return String.format("%s (b%% of a)", getName());
    }
}
