package com.openmrs.utils;

import java.time.Duration;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.openmrs.base.BasePage;
import com.openmrs.driver.DriverManager;


public final class ScrollUtils  {

	
	protected static WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
	protected static final Logger logger   =  Logger.getLogger(BasePage.class.getName());

	
	/**
	 * @author Palpandi
	 * @implNote Method will scroll to the page to the particular position on the
	 *           webpage by passing the destination as webelement where you wanted
	 *           to land on the page
	 * @param scrollElement
	 * @throws InterruptedException 
	 */
	public static void scrollToAnElement(By scrollElement) {
		WebElement element = DriverManager.getDriver().findElement(scrollElement);
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", element);
		wait.until(ExpectedConditions.visibilityOfElementLocated(scrollElement));
	}
	
	/**
	 * @author Palpandi
	 * @Usage Scrolls the page until the specified element is in view.
	 * @param scrollElement The WebElement to scroll into view.
	 */
	public static void scrollToAnElement(WebElement scrollElement) {
		//WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		WebElement element = scrollElement;
		System.out.println(" Scroll Trioggering");
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	
	/**
	 * @author Palpandi
	 * @implNote Method will move the scroll to the bottom of the page based on the
	 *           screen size
	 */
	public static void scrollBottomOfPage() {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		Long pageHeight = (Long) js.executeScript("return document.body.scrollHeight");
		js.executeScript("window.scrollTo(0, arguments[0]);", pageHeight);
	}


	/**
	 * @author Palpandi
	 * @implNote This method scrolls to the bottom of the page based on the current
	 *           screen size.
	 */
	public static void scrollTopOfPage() {
		System.out.println(" Scrolled to the top of the page ");
		Actions actions = new Actions(DriverManager.getDriver());
		actions.sendKeys(Keys.HOME).perform();
	}


	/**
	 * @author - Palpandi
	 * @implNote - Method will scroll to the page to the particular position on the
	 *             web page by passing the destination as webElement where you wanted
	 *             to land on the page
	 * @param - scrollElement
	 */
	public static void scrollToElementPosition(By scrollElement) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		WebElement element = DriverManager.getDriver().findElement(scrollElement);
		while (!element.isDisplayed()) {
			js.executeScript("window.scrollBy(0, 100)");
		}

	}

	
	/**
	 * @author Palpandi
	 * @implNote Method will scroll the page to particular position based on the x
	 *           and y axis
	 */
	public static void scrollToCurrentPosition() {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		Long currentPosition = (Long) js.executeScript("return window.pageYOffset");
		if (currentPosition < 500) {
			js.executeScript("window.scrollBy(0, 500)");
		} else {
			js.executeScript("window.scrollBy(0, -500)");
		}
	}
	

   /**
	* @author Palpandi
	* @param scrollElement
	* @implNote Method will scroll the page by using the mouse action using x and y
	*           axis
	*/
	public static void scrollUsingMouse(By scrollElement) {
		Actions actions = new Actions(DriverManager.getDriver());
		WebElement element = DriverManager.getDriver().findElement(scrollElement);
		actions.moveToElement(element).clickAndHold().moveByOffset(0, 100).release().perform();
	}


	/**
	 * @author     Palpandi
	 * @param    - scrollElement
	 * @implNote - Method will scroll the page to top by using the keyboard action by
	 *             using the target webElement
	 */
	public static void scrollUsingKeyboard(By xpath) {
		WebElement element = DriverManager.getDriver().findElement(xpath);
	    //element.click();
		element.sendKeys(Keys.PAGE_UP);

	}


	/**
	 * @author Palpandi
	 * @implNote Method will allow to scroll the inner Scrolls
	 * @param xpath
	 */
	public static void tableScroll(By xpath) {
		WebElement element = DriverManager.getDriver().findElement(xpath);
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}


	/**
	 * @author Palpandi
	 * @param scrollPath The By selector identifying the element to scroll to.
	 * @implSpec Scrolls to the specified element identified by the given By
	 *           selector using JavaScript.
	 * 
	 */
	protected void innerScrollDown(By scrollPath) {

		logger.info(" Scrolling happening into this position " + scrollPath);
		WebElement scroll = DriverManager.getDriver().findElement(scrollPath);
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", scroll);
	}
	
}
