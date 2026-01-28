package com.learning.calculator.operations.basic;

import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Addition operation implementation.
 * Performs addition of two numbers: a + b
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class Addition implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(Addition.class);

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing addition: {} + {}", a, b);
        double result = a + b;
        logger.debug("Addition result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "+";
    }

    @Override
    public String getName() {
        return "Addition";
    }
}
