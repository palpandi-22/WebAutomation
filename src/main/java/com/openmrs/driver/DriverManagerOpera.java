
package com.openmrs.driver;

import org.openqa.selenium.WebDriver;

public class DriverManagerOpera implements DriverManager_OC {


	/**
	 * @author Palpandi
	 * @implNote Method will initialize Opera driver instance
	 */
	@Override
	public WebDriver createDriver() {
		return null;
		/*
		 * WebDriver driver=new operadriver(); driver.manage().window().maximize();
		 * return driver;
		 */	}
}
