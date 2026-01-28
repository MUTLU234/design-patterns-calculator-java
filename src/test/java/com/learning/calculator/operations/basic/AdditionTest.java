package com.learning.calculator.operations.basic;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for Addition operation.
 * Demonstrates comprehensive testing with JUnit 5 and AssertJ.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
@DisplayName("Addition Operation Tests")
class AdditionTest {

    private final Addition addition = new Addition();

    @Test
    @DisplayName("Should add two positive numbers correctly")
    void testAddPositiveNumbers() {
        // Arrange
        double a = 5.0;
        double b = 3.0;
        double expected = 8.0;

        // Act
        double result = addition.execute(a, b);

        // Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    @DisplayName("Should add negative numbers correctly")
    void testAddNegativeNumbers() {
        double result = addition.execute(-5.0, -3.0);
        assertThat(result).isEqualTo(-8.0);
    }

    @Test
    @DisplayName("Should add positive and negative numbers")
    void testAddMixedNumbers() {
        double result = addition.execute(10.0, -3.0);
        assertThat(result).isEqualTo(7.0);
    }

    @Test
    @DisplayName("Should handle zero correctly")
    void testAddWithZero() {
        assertThat(addition.execute(5.0, 0.0)).isEqualTo(5.0);
        assertThat(addition.execute(0.0, 5.0)).isEqualTo(5.0);
        assertThat(addition.execute(0.0, 0.0)).isEqualTo(0.0);
    }

    @Test
    @DisplayName("Should handle decimal numbers")
    void testAddDecimalNumbers() {
        double result = addition.execute(1.5, 2.3);
        assertThat(result).isCloseTo(3.8, org.assertj.core.data.Offset.offset(0.0001));
    }

    @Test
    @DisplayName("Should handle large numbers")
    void testAddLargeNumbers() {
        double result = addition.execute(1_000_000.0, 2_000_000.0);
        assertThat(result).isEqualTo(3_000_000.0);
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "10, 20, 30",
            "-5, 5, 0",
            "0.1, 0.2, 0.3",
            "100, -50, 50"
    })
    @DisplayName("Parameterized addition tests")
    void testAdditionParameterized(double a, double b, double expected) {
        double result = addition.execute(a, b);
        assertThat(result).isCloseTo(expected, org.assertj.core.data.Offset.offset(0.0001));
    }

    @Test
    @DisplayName("Should return correct symbol")
    void testGetSymbol() {
        assertThat(addition.getSymbol()).isEqualTo("+");
    }

    @Test
    @DisplayName("Should return correct name")
    void testGetName() {
        assertThat(addition.getName()).isEqualTo("Addition");
    }

    @Test
    @DisplayName("Should have non-null description")
    void testGetDescription() {
        assertThat(addition.getDescription()).isNotNull();
        assertThat(addition.getDescription()).contains("Addition");
        assertThat(addition.getDescription()).contains("+");
    }
}
