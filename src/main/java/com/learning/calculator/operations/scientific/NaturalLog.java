package com.learning.calculator.operations.scientific;

import com.learning.calculator.exceptions.InvalidInputException;
import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Natural Logarithm operation.
 * Calculates the natural logarithm (base e) of a: ln(a)
 * Note: The second parameter 'b' is ignored in this operation.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class NaturalLog implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(NaturalLog.class);

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing natural logarithm: ln({})", a);

        if (a <= 0) {
            logger.error("Invalid logarithm input: ln({})", a);
            throw new InvalidInputException(
                    String.format("Cannot calculate logarithm of non-positive number: %.2f", a));
        }

        double result = Math.log(a);
        logger.debug("Natural logarithm result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "ln";
    }

    @Override
    public String getName() {
        return "Natural Logarithm";
    }
}
