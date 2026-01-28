package com.learning.calculator.core;

import com.learning.calculator.exceptions.DivisionByZeroException;
import com.learning.calculator.exceptions.InvalidOperationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for CalculatorEngine.
 * Tests the core business logic and coordination.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
@DisplayName("CalculatorEngine Tests")
class CalculatorEngineTest {

    private CalculatorEngine engine;

    @BeforeEach
    void setUp() {
        engine = new CalculatorEngine();
    }

    @Test
    @DisplayName("Should perform addition correctly")
    void testAddition() {
        double result = engine.calculate(1, 5.0, 3.0);
        assertThat(result).isEqualTo(8.0);
    }

    @Test
    @DisplayName("Should perform subtraction correctly")
    void testSubtraction() {
        double result = engine.calculate(2, 10.0, 3.0);
        assertThat(result).isEqualTo(7.0);
    }

    @Test
    @DisplayName("Should perform multiplication correctly")
    void testMultiplication() {
        double result = engine.calculate(3, 4.0, 5.0);
        assertThat(result).isEqualTo(20.0);
    }

    @Test
    @DisplayName("Should perform division correctly")
    void testDivision() {
        double result = engine.calculate(4, 10.0, 2.0);
        assertThat(result).isEqualTo(5.0);
    }

    @Test
    @DisplayName("Should throw exception for invalid operation")
    void testInvalidOperation() {
        assertThatThrownBy(() -> engine.calculate(999, 5.0, 3.0))
                .isInstanceOf(InvalidOperationException.class);
    }

    @Test
    @DisplayName("Should throw exception for division by zero")
    void testDivisionByZero() {
        assertThatThrownBy(() -> engine.calculate(4, 10.0, 0.0))
                .isInstanceOf(DivisionByZeroException.class);
    }

    @Test
    @DisplayName("Should add calculation to history")
    void testHistoryRecording() {
        engine.calculate(1, 5.0, 3.0);
        assertThat(engine.getHistory().size()).isEqualTo(1);

        engine.calculate(2, 10.0, 2.0);
        assertThat(engine.getHistory().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Should clear history")
    void testClearHistory() {
        engine.calculate(1, 5.0, 3.0);
        engine.calculate(2, 10.0, 2.0);

        assertThat(engine.getHistory().size()).isEqualTo(2);

        engine.clearHistory();
        assertThat(engine.getHistory().isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Should calculate with details and include execution time")
    void testCalculateWithDetails() {
        CalculatorEngine.CalculationResult result = engine.calculateWithDetails(1, 5.0, 3.0);

        assertThat(result.getResult()).isEqualTo(8.0);
        assertThat(result.getOperand1()).isEqualTo(5.0);
        assertThat(result.getOperand2()).isEqualTo(3.0);
        assertThat(result.getOperationName()).isEqualTo("Addition");
        assertThat(result.getOperationSymbol()).isEqualTo("+");
        assertThat(result.getExecutionTimeMs()).isGreaterThanOrEqualTo(0.0);
    }

    @Test
    @DisplayName("Should validate operation codes correctly")
    void testOperationValidation() {
        assertThat(engine.isValidOperation(1)).isTrue(); // Addition
        assertThat(engine.isValidOperation(4)).isTrue(); // Division
        assertThat(engine.isValidOperation(999)).isFalse(); // Invalid
    }

    @Test
    @DisplayName("Should handle scientific mode")
    void testScientificMode() {
        assertThat(engine.isScientificModeEnabled()).isFalse();

        engine.setScientificMode(true);
        assertThat(engine.isScientificModeEnabled()).isTrue();

        engine.setScientificMode(false);
        assertThat(engine.isScientificModeEnabled()).isFalse();
    }

    @Test
    @DisplayName("Should perform power operation")
    void testPowerOperation() {
        double result = engine.calculate(5, 2.0, 3.0); // 2^3
        assertThat(result).isEqualTo(8.0);
    }

    @Test
    @DisplayName("Should perform square root operation")
    void testSquareRootOperation() {
        double result = engine.calculate(6, 16.0, 0.0); // √16
        assertThat(result).isEqualTo(4.0);
    }
}
