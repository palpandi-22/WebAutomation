package com.openmrs.factories;


import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.openmrs.constants.FrameworkConstants;
import com.openmrs.driver.DriverManager;
import com.openmrs.enums.WaitStrategy;


public final class ExplicitWaitFactory {

	private ExplicitWaitFactory() {
	}



   /**
	* @author   : Palpandi
	* @implSpec : an explicit wait for a web element based on the specified wait strategy.
	* @param    : waitStrategy The wait strategy to apply for the element.
	* @param    : by The By locator used to identify the element.
	* @return   : The WebElement instance once it becomes interactable or visible.
	* @implNote : - This method will return a WebElement instance and make the execution wait until the given element becomes
	*               visible or interactable based on the provided wait strategy. The wait duration is determined by the configuration
	*               in FrameworkConstants.getExplicitWait().
	*/
	public static WebElement performExplicitWait(WaitStrategy waitStrategy, By by) {
		WebElement element = null;
		if (waitStrategy == WaitStrategy.CLICKABLE) {
			element = new WebDriverWait(DriverManager.getDriver(),
					Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
					.until(ExpectedConditions.elementToBeClickable(by));
		} else if (waitStrategy == WaitStrategy.PRESENCE) {
			element = new WebDriverWait(DriverManager.getDriver(),
					Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
					.until(ExpectedConditions.presenceOfElementLocated(by));
		} else if (waitStrategy == WaitStrategy.VISIBLE) {
			element = new WebDriverWait(DriverManager.getDriver(),
					Duration.ofSeconds(FrameworkConstants.getExplicitWait()))
					.until(ExpectedConditions.visibilityOfElementLocated(by));
		} else if (waitStrategy == WaitStrategy.NONE) {
			System.out.println("Not Waiting for anything");
			element = DriverManager.getDriver().findElement(by);
		}
		return element;
	}


}
