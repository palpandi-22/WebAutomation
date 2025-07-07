package com.openmrs.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverManagerChrome implements DriverManager_OC {


	/**
	 * @author Palpandi
	 * 
	 * Creates and configures a WebDriver instance for Chrome.
	 *
	 * This method initializes a new WebDriver instance for the Chrome browser and configures it
	 * with settings such as maximizing the browser window and deleting all cookies.
	 *
	 * @return A configured WebDriver instance for the Chrome browser.
	 */
	@Override
	public WebDriver createDriver() {
	    // Initialize a WebDriver instance for Chrome
	    WebDriver driver = new ChromeDriver();

	    // Maximize the browser window
	    driver.manage().window().maximize();

	    // Delete all cookies for a clean session
	    driver.manage().deleteAllCookies();

	    return driver;
	}


	

}
