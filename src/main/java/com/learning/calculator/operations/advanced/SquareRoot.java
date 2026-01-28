package com.learning.calculator.operations.advanced;

import com.learning.calculator.exceptions.NegativeSquareRootException;
import com.learning.calculator.operations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Square Root operation implementation.
 * Calculates the square root of a: √a
 * Note: The second parameter 'b' is ignored in this operation.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class SquareRoot implements Operation {
    private static final Logger logger = LoggerFactory.getLogger(SquareRoot.class);

    @Override
    public double execute(double a, double b) {
        logger.debug("Executing square root: √{}", a);

        if (a < 0) {
            logger.error("Negative square root attempted: √{}", a);
            throw new NegativeSquareRootException(
                    String.format("Cannot calculate square root of negative number: %.2f", a));
        }

        double result = Math.sqrt(a);
        logger.debug("Square root result: {}", result);
        return result;
    }

    @Override
    public String getSymbol() {
        return "√";
    }

    @Override
    public String getName() {
        return "Square Root";
    }
}
