package com.openmrs.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverManagerFirefox implements DriverManager_OC {

	/**
	 * 
	 * @author Palpandi
	 * 
	 * Creates and configures a WebDriver instance for the Mozilla Firefox browser.
	 *
	 * This method initializes a new WebDriver instance for the Mozilla Firefox browser and configures it
	 * by maximizing the browser window and deleting all cookies.
	 *
	 * @return A configured WebDriver instance for the Mozilla Firefox browser.
	 */
	@Override
	public WebDriver createDriver() {
	    // Initialize a WebDriver instance for Mozilla Firefox
	    WebDriver driver = new FirefoxDriver();

	    // Maximize the browser window
	    driver.manage().window().maximize();

	    // Delete all cookies for a clean session
	    driver.manage().deleteAllCookies();

	    return driver;
	}


}
