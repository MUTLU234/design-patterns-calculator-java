package com.learning.calculator.history;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the history of all calculations performed.
 * Provides thread-safe operations for storing and retrieving calculation
 * records.
 * 
 * @author Learning Java Developer
 * @version 1.0
 * @since 2026-01-28
 */
public class CalculationHistory {
    private static final Logger logger = LoggerFactory.getLogger(CalculationHistory.class);
    private static final int DEFAULT_MAX_SIZE = 100;

    private final List<CalculationRecord> records;
    private final int maxSize;

    /**
     * Constructs a new calculation history with default maximum size.
     */
    public CalculationHistory() {
        this(DEFAULT_MAX_SIZE);
    }

    /**
     * Constructs a new calculation history with specified maximum size.
     * 
     * @param maxSize maximum number of records to keep
     * @throws IllegalArgumentException if maxSize is less than 1
     */
    public CalculationHistory(int maxSize) {
        if (maxSize < 1) {
            throw new IllegalArgumentException("Max size must be at least 1");
        }
        this.maxSize = maxSize;
        this.records = new ArrayList<>();
        logger.info("Calculation history initialized with max size: {}", maxSize);
    }

    /**
     * Adds a calculation record to the history.
     * If the history exceeds maximum size, the oldest record is removed.
     * 
     * @param record the calculation record to add
     * @throws NullPointerException if record is null
     */
    public synchronized void addRecord(CalculationRecord record) {
        if (record == null) {
            throw new NullPointerException("Cannot add null record to history");
        }

        records.add(record);
        logger.debug("Added record to history: {}", record);

        // Remove oldest if exceeded max size
        while (records.size() > maxSize) {
            CalculationRecord removed = records.remove(0);
            logger.debug("Removed oldest record due to size limit: {}", removed);
        }
    }

    /**
     * Returns an unmodifiable view of all calculation records.
     * 
     * @return unmodifiable list of calculation records
     */
    public synchronized List<CalculationRecord> getRecords() {
        return Collections.unmodifiableList(new ArrayList<>(records));
    }

    /**
     * Returns the most recent N calculation records.
     * 
     * @param count number of recent records to return
     * @return list of recent records
     */
    public synchronized List<CalculationRecord> getRecentRecords(int count) {
        int fromIndex = Math.max(0, records.size() - count);
        return Collections.unmodifiableList(new ArrayList<>(records.subList(fromIndex, records.size())));
    }

    /**
     * Clears all calculation records from history.
     */
    public synchronized void clear() {
        int previousSize = records.size();
        records.clear();
        logger.info("Cleared calculation history. Removed {} records", previousSize);
    }

    /**
     * Returns the number of records in history.
     * 
     * @return number of records
     */
    public synchronized int size() {
        return records.size();
    }

    /**
     * Checks if history is empty.
     * 
     * @return true if history has no records
     */
    public synchronized boolean isEmpty() {
        return records.isEmpty();
    }

    /**
     * Gets the maximum size of this history.
     * 
     * @return maximum size
     */
    public int getMaxSize() {
        return maxSize;
    }
}
