package com.learning.calculator.operations.scientific;

import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Cosine trigonometric operation.
 * Calculates the cosine of angle a (in degrees).
 * Note: The second parameter 'b' is ignored in this operation.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class Cosine implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(Cosine.class);

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing cosine: cos({}°)", a);
        double radians = Math.toRadians(a);
        double result = Math.cos(radians);
        logger.debug("Cosine result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "cos";
    }

    @Override
    public String getName() {
        return "Cosine";
    }
}
