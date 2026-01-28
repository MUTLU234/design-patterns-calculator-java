package com.learning.calculator.history;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Immutable record of a single calculation.
 * Stores all information about a calculation operation with timestamp.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public final class CalculationRecord {
    private final double operand1;
    private final double operand2;
    private final String operationSymbol;
    private final String operationName;
    private final double result;
    private final LocalDateTime timestamp;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Constructs a new calculation record.
     * 
     * @param operand1        first operand
     * @param operand2        second operand
     * @param operationSymbol symbol of the operation
     * @param operationName   name of the operation
     * @param result          result of the calculation
     */
    public CalculationRecord(double operand1, double operand2,
            String operationSymbol, String operationName, double result) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operationSymbol = Objects.requireNonNull(operationSymbol, "Operation symbol cannot be null");
        this.operationName = Objects.requireNonNull(operationName, "Operation name cannot be null");
        this.result = result;
        this.timestamp = LocalDateTime.now();
    }

    // Getters
    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public String getOperationSymbol() {
        return operationSymbol;
    }

    public String getOperationName() {
        return operationName;
    }

    public double getResult() {
        return result;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s] %.4f %s %.4f = %.4f",
                timestamp.format(FORMATTER), operand1, operationSymbol, operand2, result);
    }

    /**
     * Returns a formatted string representation of this record.
     * 
     * @return formatted string
     */
    public String toFormattedString() {
        return String.format("[%s] %s: %.4f %s %.4f = %.4f",
                timestamp.format(FORMATTER), operationName, operand1, operationSymbol, operand2, result);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CalculationRecord that = (CalculationRecord) o;
        return Double.compare(that.operand1, operand1) == 0 &&
                Double.compare(that.operand2, operand2) == 0 &&
                Double.compare(that.result, result) == 0 &&
                operationSymbol.equals(that.operationSymbol) &&
                timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand1, operand2, operationSymbol, result, timestamp);
    }
}
