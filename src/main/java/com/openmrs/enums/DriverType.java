package com.openmrs.enums;

/**
 * 
 * @author Palpandi
 * 
 * The {@code DriverType} enum represents different web browser types used for web automation testing.
 * These predefined browser types can be used to specify the type of browser to be used during testing.
 *
 * Usage:
 * <pre>
 * {@code
 * // Select a browser type for test configuration
 * DriverType browserType = DriverType.CHROME;
 *
 * // Use the browser type to configure a WebDriver instance
 * WebDriver driver = DriverManager.getManager(DriverType.FIREFOX).createDriver();
 * }
 * </pre>
 */
public enum DriverType {
   
	CHROME,
    FIREFOX,
    EDGE,
    SAFARI,
    OPERA,
}
