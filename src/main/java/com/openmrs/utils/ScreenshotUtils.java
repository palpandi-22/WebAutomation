package com.openmrs.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.openmrs.driver.DriverManager;

public final class ScreenshotUtils {
	private ScreenshotUtils() {
	}


	/**
	 * @author Palpandi
	 * @return
	 * @implNote Method will capture the screenshot when ever its called and save it as Base64
	 */
	public static String getBase64Image() {
		return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
	}
}
