package com.openmrs.driver;

import org.openqa.selenium.WebDriver;

/**
 * 
 * @author Palpandi
 * 
 * The {@code DriverManager} class provides a simple mechanism for managing WebDriver instances in a multi-threaded environment using 
 * ThreadLocal.
 * 
 * It allows you to set and retrieve WebDriver instances specific to each thread, making it suitable for parallel test execution.
 *
 * Usage:
 * <pre>
 * {@code
 * // Set a WebDriver instance for the current thread
 * WebDriver driver = new ChromeDriver();
 * DriverManager.setDriver(driver);
 *
 * // Retrieve the WebDriver instance for the current thread
 * WebDriver currentDriver = DriverManager.getDriver();
 *
 * // Perform testing actions with the WebDriver instance
 * currentDriver.get("https://www.example.com");
 *
 * // Clean up and remove the WebDriver instance for the current thread
 * DriverManager.unload();
 * }
 * </pre>
 *
 * ThreadLocal is used to ensure that each thread has its own isolated WebDriver instance, preventing interference
 * between parallel test executions.
 *
 * Note: It is essential to call the {@code unload} method to remove the WebDriver instance when the test is completed
 * to avoid memory leaks.
 *
 * @see ThreadLocal
 * @see WebDriver
 */
public class DriverManager {

	// Private constructor to prevent instantiation of the class
	private DriverManager() {
	}

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Get the WebDriver instance associated with the current thread.
     *
     * @return The WebDriver instance for the current thread.
     */
    public static WebDriver getDriver() {
        return driver.get();
    }
    
   

    /**
     * Set the WebDriver instance for the current thread.
     *
     * @param driverref The WebDriver instance to set for the current thread.
     */
    public static void setDriver(WebDriver driverref) {
        driver.set(driverref);
    }

    /**
     * Remove the WebDriver instance associated with the current thread.
     * This should be called after the test is completed to avoid memory leaks.
     */
    public static void unload() {
        driver.remove();
    }
}

