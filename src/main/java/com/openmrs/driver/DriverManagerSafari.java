package com.openmrs.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManagerSafari implements DriverManager_OC {


	/**
	 * 
	 * @author Palpandi
	 * 
	 * Creates and configures a WebDriver instance for the Apple Safari browser.
	 *
	 * This method initializes a new WebDriver instance for the Apple Safari browser and configures it
	 * by maximizing the browser window.
	 *
	 * @return A configured WebDriver instance for the Apple Safari browser.
	 */
	@Override
	public WebDriver createDriver() {
	    // Initialize a WebDriver instance for Apple Safari
	    WebDriver driver = new SafariDriver();

	    // Maximize the browser window
	    driver.manage().window().maximize();

	    return driver;
	}


}
