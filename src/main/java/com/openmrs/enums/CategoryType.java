package com.openmrs.enums;

/**
 * 
 * @author Palpandi
 * 
 * The {@code CategoryType} enum represents different test categories commonly used in software testing.
 * These predefined categories can be associated with specific test cases or scenarios.
 *
 * Usage:
 * <pre>
 * {@code
 * // Assign a category to a test case or scenario
 * CategoryType category = CategoryType.REGRESSION;
 *
 * // Use the category in test case documentation or metadata
 * @Test(description = "Verify login functionality", category = CategoryType.SMOKE)
 * public void testLogin() {
 *     // Test implementation
 * }
 * }
 * </pre>
 */
public enum CategoryType {
 
	REGRESSION,
    SMOKE,
    SANITY,
    ENDTOEND,
    SECURITY,
    PAGELOADING,
    
}

