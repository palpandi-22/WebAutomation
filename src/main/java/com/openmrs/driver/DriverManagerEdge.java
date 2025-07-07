package com.openmrs.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class DriverManagerEdge implements DriverManager_OC {

	/**
	 * 
	 * @author Palpandi
	 * 
	 * Creates and configures a WebDriver instance for the Microsoft Edge browser.
	 *
	 * This method initializes a new WebDriver instance for the Microsoft Edge browser and configures it
	 * by maximizing the browser window.
	 *
	 * @return A configured WebDriver instance for the Microsoft Edge browser.
	 */
	@Override
	public WebDriver createDriver() {
	    // Initialize a WebDriver instance for Microsoft Edge
	    WebDriver driver = new EdgeDriver();

	    // Maximize the browser window
	    driver.manage().window().maximize();

	    return driver;
	}




}
