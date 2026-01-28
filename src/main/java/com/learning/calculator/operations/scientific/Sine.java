package com.learning.calculator.operations.scientific;

import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Sine trigonometric operation.
 * Calculates the sine of angle a (in degrees).
 * Note: The second parameter 'b' is ignored in this operation.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class Sine implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(Sine.class);

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing sine: sin({}°)", a);
        double radians = Math.toRadians(a);
        double result = Math.sin(radians);
        logger.debug("Sine result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "sin";
    }

    @Override
    public String getName() {
        return "Sine";
    }
}
