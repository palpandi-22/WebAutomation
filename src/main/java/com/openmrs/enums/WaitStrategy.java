package com.openmrs.enums;

/**
 * 
 * @author Palpandi
 * 
 * The {@code WaitStrategy} enum represents different strategies for waiting for elements on a web page to become interactable.
 * These predefined constants provide options for specifying how to wait for elements to be ready for user interaction.
 *
 * Usage:
 * <pre>
 * {@code
 * // Select a wait strategy, for example, "Clickable," to wait for an element to be clickable.
 * WaitStrategy strategy = WaitStrategy.CLICKABLE;
 *
 * // Use the selected wait strategy in your code to apply waiting conditions.
 * // Example: Wait for an element to be clickable before performing an action.
 * }
 * </pre>
 */
public enum WaitStrategy {
    CLICKABLE,
    PRESENCE,
    VISIBLE,
    NONE
}

