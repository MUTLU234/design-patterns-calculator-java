package com.learning.calculator.core;

import com.learning.calculator.history.CalculationHistory;
import com.learning.calculator.history.CalculationRecord;
import com.learning.calculator.operations.Operation;
import com.learning.calculator.operations.factory.OperationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Core calculator engine that performs calculations and manages history.
 * This class is the heart of the calculator application, coordinating
 * between operations, history, and business logic.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class CalculatorEngine {
    private static final Logger logger = LoggerFactory.getLogger(CalculatorEngine.class);

    private final OperationFactory operationFactory;
    private final CalculationHistory history;
    private boolean scientificModeEnabled;

    /**
     * Constructs a new Calculator Engine with default settings.
     */
    public CalculatorEngine() {
        this.operationFactory = OperationFactory.getInstance();
        this.history = new CalculationHistory();
        this.scientificModeEnabled = false;
        logger.info("CalculatorEngine initialized");
    }

    /**
     * Constructs a new Calculator Engine with custom history size.
     * 
     * @param maxHistorySize maximum number of calculation records to keep
     */
    public CalculatorEngine(int maxHistorySize) {
        this.operationFactory = OperationFactory.getInstance();
        this.history = new CalculationHistory(maxHistorySize);
        this.scientificModeEnabled = false;
        logger.info("CalculatorEngine initialized with max history size: {}", maxHistorySize);
    }

    /**
     * Performs a calculation based on the operation code and operands.
     * Automatically records the calculation in history.
     * 
     * @param operationCode code identifying the operation to perform
     * @param operand1      first operand
     * @param operand2      second operand
     * @return the result of the calculation
     * @throws com.learning.calculator.exceptions.InvalidOperationException if
     *                                                                      operation
     *                                                                      code is
     *                                                                      invalid
     * @throws com.learning.calculator.exceptions.CalculatorException       if
     *                                                                      calculation
     *                                                                      fails
     */
    public double calculate(int operationCode, double operand1, double operand2) {
        logger.info("Calculating: operation={}, operand1={}, operand2={}",
                operationCode, operand1, operand2);

        // Get the operation from factory
        Operation operation = operationFactory.createOperation(operationCode);

        // Execute the operation
        double result;
        try {
            result = operation.execute(operand1, operand2);
            logger.info("Calculation successful: {} {} {} = {}",
                    operand1, operation.getSymbol(), operand2, result);
        } catch (Exception e) {
            logger.error("Calculation failed: {}", e.getMessage(), e);
            throw e;
        }

        // Record in history
        CalculationRecord record = new CalculationRecord(
                operand1, operand2, operation.getSymbol(), operation.getName(), result);
        history.addRecord(record);

        return result;
    }

    /**
     * Performs a calculation with detailed result information.
     * 
     * @param operationCode code identifying the operation to perform
     * @param operand1      first operand
     * @param operand2      second operand
     * @return CalculationResult containing the result and metadata
     */
    public CalculationResult calculateWithDetails(int operationCode, double operand1, double operand2) {
        long startTime = System.nanoTime();
        double result = calculate(operationCode, operand1, operand2);
        long endTime = System.nanoTime();

        double executionTimeMs = (endTime - startTime) / 1_000_000.0;
        Operation operation = operationFactory.createOperation(operationCode);

        return new CalculationResult(operand1, operand2, result,
                operation.getName(), operation.getSymbol(), executionTimeMs);
    }

    /**
     * Gets the calculation history.
     * 
     * @return the calculation history
     */
    public CalculationHistory getHistory() {
        return history;
    }

    /**
     * Clears the calculation history.
     */
    public void clearHistory() {
        history.clear();
        logger.info("History cleared");
    }

    /**
     * Enables or disables scientific mode.
     * 
     * @param enabled true to enable scientific mode
     */
    public void setScientificMode(boolean enabled) {
        this.scientificModeEnabled = enabled;
        logger.info("Scientific mode {}", enabled ? "enabled" : "disabled");
    }

    /**
     * Checks if scientific mode is enabled.
     * 
     * @return true if scientific mode is enabled
     */
    public boolean isScientificModeEnabled() {
        return scientificModeEnabled;
    }

    /**
     * Validates if an operation code is valid.
     * 
     * @param operationCode the operation code to validate
     * @return true if the operation code is valid
     */
    public boolean isValidOperation(int operationCode) {
        return operationFactory.isValidOperationCode(operationCode);
    }

    /**
     * Inner class representing a detailed calculation result.
     */
    public static class CalculationResult {
        private final double operand1;
        private final double operand2;
        private final double result;
        private final String operationName;
        private final String operationSymbol;
        private final double executionTimeMs;

        public CalculationResult(double operand1, double operand2, double result,
                String operationName, String operationSymbol, double executionTimeMs) {
            this.operand1 = operand1;
            this.operand2 = operand2;
            this.result = result;
            this.operationName = operationName;
            this.operationSymbol = operationSymbol;
            this.executionTimeMs = executionTimeMs;
        }

        // Getters
        public double getOperand1() {
            return operand1;
        }

        public double getOperand2() {
            return operand2;
        }

        public double getResult() {
            return result;
        }

        public String getOperationName() {
            return operationName;
        }

        public String getOperationSymbol() {
            return operationSymbol;
        }

        public double getExecutionTimeMs() {
            return executionTimeMs;
        }

        @Override
        public String toString() {
            return String.format("%s: %.4f %s %.4f = %.4f (executed in %.3f ms)",
                    operationName, operand1, operationSymbol, operand2, result, executionTimeMs);
        }
    }
}
