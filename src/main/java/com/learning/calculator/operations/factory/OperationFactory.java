package com.learning.calculator.operations.factory;

import com.learning.calculator.exceptions.InvalidOperationException;
import com.learning.calculator.operations.Operation;
import com.learning.calculator.operations.advanced.*;
import com.learning.calculator.operations.basic.*;
import com.learning.calculator.operations.scientific.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Factory class for creating Operation instances.
 * Implements the Factory Pattern for centralized operation creation.
 * Uses Singleton pattern to ensure only one factory instance exists.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class OperationFactory {
    private static final Logger logger = LoggerFactory.getLogger(OperationFactory.class);
    private static OperationFactory instance;

    private final Map<Integer, Operation> basicOperations;
    private final Map<Integer, Operation> scientificOperations;

    /**
     * Private constructor to enforce Singleton pattern.
     */
    private OperationFactory() {
        basicOperations = new HashMap<>();
        scientificOperations = new HashMap<>();
        initializeOperations();
        logger.info("OperationFactory initialized with {} basic and {} scientific operations",
                basicOperations.size(), scientificOperations.size());
    }

    /**
     * Gets the singleton instance of OperationFactory.
     * 
     * @return the factory instance
     */
    public static synchronized OperationFactory getInstance() {
        if (instance == null) {
            instance = new OperationFactory();
        }
        return instance;
    }

    /**
     * Initializes all available operations.
     */
    private void initializeOperations() {
        // Basic operations
        basicOperations.put(1, new Addition());
        basicOperations.put(2, new Subtraction());
        basicOperations.put(3, new Multiplication());
        basicOperations.put(4, new Division());

        // Advanced operations
        basicOperations.put(5, new Power());
        basicOperations.put(6, new SquareRoot());
        basicOperations.put(7, new Modulus());
        basicOperations.put(8, new Percentage());

        // Scientific operations
        scientificOperations.put(11, new Sine());
        scientificOperations.put(12, new Cosine());
        scientificOperations.put(13, new Tangent());
        scientificOperations.put(14, new NaturalLog());
    }

    /**
     * Creates an operation based on the operation code.
     * 
     * @param operationCode the code identifying the operation
     * @return the Operation instance
     * @throws InvalidOperationException if the operation code is invalid
     */
    public Operation createOperation(int operationCode) {
        Operation operation = basicOperations.get(operationCode);

        if (operation == null) {
            operation = scientificOperations.get(operationCode);
        }

        if (operation == null) {
            logger.error("Invalid operation code: {}", operationCode);
            throw new InvalidOperationException(
                    String.format("Invalid operation code: %d", operationCode));
        }

        logger.debug("Created operation: {} (code: {})", operation.getName(), operationCode);
        return operation;
    }

    /**
     * Gets all basic operation codes.
     * 
     * @return set of basic operation codes
     */
    public Set<Integer> getBasicOperationCodes() {
        return basicOperations.keySet();
    }

    /**
     * Gets all scientific operation codes.
     * 
     * @return set of scientific operation codes
     */
    public Set<Integer> getScientificOperationCodes() {
        return scientificOperations.keySet();
    }

    /**
     * Checks if an operation code is valid.
     * 
     * @param operationCode the code to check
     * @return true if the operation code is valid
     */
    public boolean isValidOperationCode(int operationCode) {
        return basicOperations.containsKey(operationCode) ||
                scientificOperations.containsKey(operationCode);
    }

    /**
     * Gets the operation name for a given code.
     * 
     * @param operationCode the operation code
     * @return the operation name, or "Unknown" if code is invalid
     */
    public String getOperationName(int operationCode) {
        Operation operation = basicOperations.get(operationCode);
        if (operation == null) {
            operation = scientificOperations.get(operationCode);
        }
        return operation != null ? operation.getName() : "Unknown";
    }
}
