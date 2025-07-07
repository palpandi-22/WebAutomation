package com.openmrs.enums;

/**
 * 
 * @author Palpandi
 * 
 * The {@code DropdownType} enum represents various actions that can be performed on a dropdown or select element
 * during web automation testing. These predefined options specify how to interact with the dropdown.
 *
 * Usage:
 * <pre>
 * {@code
 * // Select an action to perform on a dropdown
 * DropdownType action = DropdownType.selectByValue;
 *
 * // Use the action in your test automation code to interact with the dropdown
 * Select dropdown = new Select(element);
 * switch (action) {
 *     case selectByIndex:
 *         dropdown.selectByIndex(index);
 *         break;
 *     case selectByValue:
 *         dropdown.selectByValue(value);
 *         break;
 *     // Handle other actions similarly
 * }
 * }
 * </pre>
 */
public enum DropdownType {
    selectByIndex,
    selectByValue,
    selectByVisibleText,
    deselectAll,
    deselectByIndex,
    deselectByValue,
    deselectByVisibleText
}

