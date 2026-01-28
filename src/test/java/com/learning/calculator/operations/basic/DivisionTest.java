package com.learning.calculator.operations.basic;

import com.learning.calculator.exceptions.DivisionByZeroException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Unit tests for Division operation.
 * Focuses on edge cases and exception handling.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
@DisplayName("Division Operation Tests")
class DivisionTest {

    private final Division division = new Division();

    @Test
    @DisplayName("Should divide two positive numbers correctly")
    void testDividePositiveNumbers() {
        double result = division.execute(10.0, 2.0);
        assertThat(result).isEqualTo(5.0);
    }

    @Test
    @DisplayName("Should throw exception when dividing by zero")
    void testDivideByZero() {
        assertThatThrownBy(() -> division.execute(10.0, 0.0))
                .isInstanceOf(DivisionByZeroException.class)
                .hasMessageContaining("Cannot divide");
    }

    @Test
    @DisplayName("Should throw exception when dividing by very small number close to zero")
    void testDivideByVerySmallNumber() {
        assertThatThrownBy(() -> division.execute(10.0, 1e-11))
                .isInstanceOf(DivisionByZeroException.class);
    }

    @Test
    @DisplayName("Should handle decimal results correctly")
    void testDecimalResult() {
        double result = division.execute(7.0, 2.0);
        assertThat(result).isEqualTo(3.5);
    }

    @Test
    @DisplayName("Should handle negative numbers")
    void testDivideNegativeNumbers() {
        assertThat(division.execute(-10.0, 2.0)).isEqualTo(-5.0);
        assertThat(division.execute(10.0, -2.0)).isEqualTo(-5.0);
        assertThat(division.execute(-10.0, -2.0)).isEqualTo(5.0);
    }

    @Test
    @DisplayName("Should handle division by 1")
    void testDivideByOne() {
        double result = division.execute(42.0, 1.0);
        assertThat(result).isEqualTo(42.0);
    }

    @Test
    @DisplayName("Should handle zero divided by non-zero")
    void testZeroDividedByNumber() {
        double result = division.execute(0.0, 5.0);
        assertThat(result).isEqualTo(0.0);
    }

    @ParameterizedTest(name = "{0} / {1} = {2}")
    @CsvSource({
            "10, 2, 5",
            "100, 10, 10",
            "1, 2, 0.5",
            "-10, 2, -5",
            "15, 3, 5",
            "7, 2, 3.5"
    })
    @DisplayName("Parameterized division tests")
    void testDivisionParameterized(double a, double b, double expected) {
        double result = division.execute(a, b);
        assertThat(result).isCloseTo(expected, org.assertj.core.data.Offset.offset(0.0001));
    }

    @Test
    @DisplayName("Should return correct symbol")
    void testGetSymbol() {
        assertThat(division.getSymbol()).isEqualTo("/");
    }

    @Test
    @DisplayName("Should return correct name")
    void testGetName() {
        assertThat(division.getName()).isEqualTo("Division");
    }
}
