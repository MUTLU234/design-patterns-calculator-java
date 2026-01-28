package com.learning.calculator.ui;

import com.learning.calculator.core.CalculatorEngine;
import com.learning.calculator.history.CalculationRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Console-based user interface for the calculator.
 * Handles all user interactions and display logic.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class ConsoleUI {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleUI.class);

    private final Scanner scanner;
    private final CalculatorEngine engine;
    private boolean running;

    /**
     * Constructs a new console UI with the given calculator engine.
     * 
     * @param engine the calculator engine to use
     */
    public ConsoleUI(CalculatorEngine engine) {
        this.scanner = new Scanner(System.in);
        this.engine = engine;
        this.running = false;
        logger.info("ConsoleUI initialized");
    }

    /**
     * Starts the calculator application.
     */
    public void start() {
        running = true;
        displayWelcomeMessage();

        while (running) {
            try {
                displayMainMenu();
                int choice = getUserChoice();
                processChoice(choice);
            } catch (Exception e) {
                logger.error("Error in main loop", e);
                System.out.println("\n❌ Beklenmeyen bir hata oluştu: " + e.getMessage());
            }

            System.out.println(); // Empty line for readability
        }

        shutdown();
    }

    /**
     * Displays the welcome message.
     */
    private void displayWelcomeMessage() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("              PROFESSIONAL CALCULATOR v1.0");
        System.out.println("            Enterprise-Grade Java Application");
        System.out.println("=".repeat(60));
        System.out.println("✨ Featuring: Clean Code, Design Patterns, and Best Practices");
        System.out.println("=".repeat(60) + "\n");
    }

    /**
     * Displays the main menu.
     */
    private void displayMainMenu() {
        System.out.println("╔" + "═".repeat(58) + "╗");
        System.out.println("║" + " ".repeat(20) + "MAIN MENU" + " ".repeat(29) + "║");
        System.out.println("╠" + "═".repeat(58) + "╣");
        System.out.println("║  BASIC OPERATIONS" + " ".repeat(40) + "║");
        System.out.println("║    1. ➕ Addition                                         ║");
        System.out.println("║    2. ➖ Subtraction                                      ║");
        System.out.println("║    3. ✖️  Multiplication                                  ║");
        System.out.println("║    4. ➗ Division                                         ║");
        System.out.println("║" + " ".repeat(58) + "║");
        System.out.println("║  ADVANCED OPERATIONS" + " ".repeat(37) + "║");
        System.out.println("║    5. 📐 Power (a^b)                                      ║");
        System.out.println("║    6. √  Square Root                                     ║");
        System.out.println("║    7. %  Modulus                                         ║");
        System.out.println("║    8. 💯 Percentage (b% of a)                            ║");
        System.out.println("║" + " ".repeat(58) + "║");
        System.out.println("║  SCIENTIFIC OPERATIONS" + " ".repeat(35) + "║");
        System.out.println("║   11. 📊 Sine (sin)                                       ║");
        System.out.println("║   12. 📊 Cosine (cos)                                     ║");
        System.out.println("║   13. 📊 Tangent (tan)                                    ║");
        System.out.println("║   14. 📊 Natural Logarithm (ln)                           ║");
        System.out.println("║" + " ".repeat(58) + "║");
        System.out.println("║  UTILITIES" + " ".repeat(47) + "║");
        System.out.println("║   20. 📝 View History                                     ║");
        System.out.println("║   21. 🗑️  Clear History                                   ║");
        System.out.println("║    0. 🚪 Exit                                             ║");
        System.out.println("╚" + "═".repeat(58) + "╝");
    }

    /**
     * Gets the user's menu choice.
     * 
     * @return the user's choice
     */
    private int getUserChoice() {
        while (true) {
            try {
                System.out.print("\n👉 Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
                return choice;
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter a number.");
                scanner.nextLine(); // Clear invalid input
                logger.warn("Invalid input from user");
            }
        }
    }

    /**
     * Processes the user's menu choice.
     * 
     * @param choice the user's choice
     */
    private void processChoice(int choice) {
        if (choice == 0) {
            running = false;
            return;
        }

        if (choice == 20) {
            displayHistory();
            return;
        }

        if (choice == 21) {
            clearHistory();
            return;
        }

        // Handle calculation operations
        if (engine.isValidOperation(choice)) {
            performCalculation(choice);
        } else {
            System.out.println("❌ Invalid operation code! Please try again.");
            logger.warn("Invalid operation code entered: {}", choice);
        }
    }

    /**
     * Performs a calculation based on user input.
     * 
     * @param operationCode the operation code
     */
    private void performCalculation(int operationCode) {
        try {
            System.out.print("\n📊 Enter first number: ");
            double operand1 = scanner.nextDouble();
            scanner.nextLine(); // Clear buffer

            double operand2 = 0;
            // Some operations don't need second operand
            if (operationCode != 6 && operationCode < 11) {
                System.out.print("📊 Enter second number: ");
                operand2 = scanner.nextDouble();
                scanner.nextLine(); // Clear buffer
            }

            // Perform calculation with details
            CalculatorEngine.CalculationResult result = engine.calculateWithDetails(operationCode, operand1, operand2);

            // Display result
            displayResult(result);

        } catch (InputMismatchException e) {
            System.out.println("❌ Invalid number format! Please enter a valid number.");
            scanner.nextLine(); // Clear invalid input
        } catch (Exception e) {
            System.out.println("❌ Calculation error: " + e.getMessage());
            logger.error("Calculation error", e);
        }
    }

    /**
     * Displays the calculation result.
     * 
     * @param result the calculation result
     */
    private void displayResult(CalculatorEngine.CalculationResult result) {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("✅ RESULT");
        System.out.println("─".repeat(60));
        System.out.println(result.toString());
        System.out.println("─".repeat(60));
    }

    /**
     * Displays the calculation history.
     */
    private void displayHistory() {
        List<CalculationRecord> records = engine.getHistory().getRecords();

        if (records.isEmpty()) {
            System.out.println("\n📝 History is empty. No calculations yet.");
            return;
        }

        System.out.println("\n" + "═".repeat(80));
        System.out.println("                         CALCULATION HISTORY");
        System.out.println("═".repeat(80));
        System.out.println(String.format("%-25s %-40s %-10s", "Timestamp", "Expression", "Result"));
        System.out.println("─".repeat(80));

        for (CalculationRecord record : records) {
            System.out.println(record.toFormattedString());
        }

        System.out.println("═".repeat(80));
        System.out.println(String.format("Total: %d calculation(s)", records.size()));
        System.out.println("═".repeat(80));
    }

    /**
     * Clears the calculation history.
     */
    private void clearHistory() {
        engine.clearHistory();
        System.out.println("✅ History cleared successfully!");
    }

    /**
     * Shuts down the calculator UI.
     */
    private void shutdown() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("           Thank you for using Professional Calculator!");
        System.out.println("                    Goodbye! 👋");
        System.out.println("=".repeat(60) + "\n");

        scanner.close();
        logger.info("Calculator application shutdown");
    }
}
