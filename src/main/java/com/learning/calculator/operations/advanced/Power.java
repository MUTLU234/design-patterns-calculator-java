package com.learning.calculator.operations.advanced;

import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Power operation implementation.
 * Calculates a raised to the power of b: a^b
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class Power implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(Power.class);

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing power: {} ^ {}", a, b);
        double result = Math.pow(a, b);

        if (Double.isInfinite(result)) {
            logger.warn("Power operation resulted in infinity: {} ^ {}", a, b);
        } else if (Double.isNaN(result)) {
            logger.warn("Power operation resulted in NaN: {} ^ {}", a, b);
        }

        logger.debug("Power result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "^";
    }

    @Override
    public String getName() {
        return "Power";
    }
}
