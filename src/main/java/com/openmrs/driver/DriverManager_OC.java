package com.openmrs.driver;

import org.openqa.selenium.WebDriver;

import com.openmrs.enums.DriverType;

/**
 * 
 * @author Palpandi
 * 
 * The {@code DriverManager_OC} interface defines a contract for creating WebDriver instances.
 * Implementations of this interface are responsible for creating and configuring WebDriver instances
 * to be used for various testing purposes.
 *
 * Example of usage:
 * <pre>
 * {@code
 * DriverManager_OC driverManager = DriverManagerFactory.getDriverManager(DriverType.CHROME);
 * WebDriver driver = driverManager.createDriver();
 * driver.get("https://www.example.com");
 * // Perform testing actions with the WebDriver instance
 * driver.quit();
 * }
 * </pre>
 *
 * Implementations of this interface should provide appropriate configuration and management of
 * WebDriver instances based on the specific driver type they are designed for.
 *
 * @see DriverManagerFactory
 * @see WebDriver
 * @see DriverType
 */
public interface DriverManager_OC {

	/**
	 * Creates and configures a WebDriver instance based on the implementation's
	 * specific driver type.
	 *
	 * @return A configured WebDriver instance for testing.
	 */
	WebDriver createDriver();
}
