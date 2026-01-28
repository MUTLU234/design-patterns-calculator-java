package com.learning.calculator.operations.scientific;

import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tangent trigonometric operation.
 * Calculates the tangent of angle a (in degrees).
 * Note: The second parameter 'b' is ignored in this operation.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class Tangent implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(Tangent.class);

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing tangent: tan({}°)", a);
        double radians = Math.toRadians(a);
        double result = Math.tan(radians);

        if (Double.isInfinite(result)) {
            logger.warn("Tangent operation resulted in infinity for angle: {}°", a);
        }

        logger.debug("Tangent result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "tan";
    }

    @Override
    public String getName() {
        return "Tangent";
    }
}
