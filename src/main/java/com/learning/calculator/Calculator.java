package com.learning.calculator;

import com.learning.calculator.core.CalculatorEngine;
import com.learning.calculator.ui.ConsoleUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Main entry point for the Calculator application.
 * This class demonstrates enterprise-grade Java development practices
 * including:
 * - Design Patterns (Strategy, Factory, Singleton)
 * - SOLID Principles
 * - Clean Code practices
 * - Comprehensive error handling
 * - Professional logging
 * - Modular architecture
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class Calculator {
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    /**
     * Main method - application entry point.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        logger.info("Starting Calculator Application");
        logger.info("Java Version: {}", System.getProperty("java.version"));
        logger.info("OS: {} {}", System.getProperty("os.name"), System.getProperty("os.version"));

        try {
            // Initialize calculator engine
            CalculatorEngine engine = new CalculatorEngine();

            // Initialize and start UI
            ConsoleUI ui = new ConsoleUI(engine);
            ui.start();

            logger.info("Calculator Application terminated normally");

        } catch (Exception e) {
            logger.error("Fatal error in Calculator Application", e);
            System.err.println("A fatal error occurred. Please check the logs for details.");
            System.exit(1);
        }
    }
}
