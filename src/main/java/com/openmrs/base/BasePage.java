package com.openmrs.base;

import static com.openmrs.constants.FrameworkConstants.ICON_Navigate_Right;
import static com.openmrs.constants.FrameworkConstants.WAIT;
import static com.openmrs.constants.FrameworkConstants.YES;

import java.io.IOException;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.google.common.util.concurrent.Uninterruptibles;
import com.openmrs.constants.FrameworkConstants;
import com.openmrs.enums.DropdownType;
import com.openmrs.enums.PropertyLoader;
import com.openmrs.enums.WaitStrategy;
import com.openmrs.factories.ExplicitWaitFactory;
import com.openmrs.reports.ExtentLogger;
import com.openmrs.reports.ExtentManager;
import com.openmrs.utils.ConfigLoader;
import com.openmrs.utils.FileCreator;
import com.openmrs.utils.Helper;
import com.openmrs.utils.ScreenshotUtils;
import com.openmrs.utils.ScrollUtils;



public class BasePage {
	
	
	protected static final Logger logger     =  Logger.getLogger(BasePage.class.getName());
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected boolean acceptNextAlert = true;
	public static Set<Cookie> Cookies = new HashSet<>();
	
	protected WebDriverWait waitLong;
	protected WebDriverWait waitShort;
	protected WebDriverWait waitQuick;

	
	
	public BasePage(WebDriver driver) {	
		this.driver = driver;
		waitLong    = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait        = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.getExplicitWait()));
		waitShort   = new WebDriverWait(driver, Duration.ofSeconds(FrameworkConstants.getWait()));
		waitQuick   = new WebDriverWait(driver, Duration.ofSeconds(1));
	}

	/**
	 * @Author   : Palpandi
	 * @implNote : a web page by navigating to the specified end point.
	 * @Description This method is responsible for loading a web page by navigating
	 *              to the specified 'endPoint.' It constructs the URL based on the
	 *              base URL provided by the 'ConfigLoader' and the given end point.
	 *              It also checks if the user is already on the logout page, in
	 *              which case it logs in again to establish a user session.
	 * 
	 *              - The key tasks performed by this method include: - Constructing
	 *              the URL: It constructs the URL by combining the base URL from
	 *              'ConfigLoader' and the 'endPoint' parameter.
	 * 
	 *              - Checking for logout page: It checks if the current URL is the
	 *              logout page, and if so, it logs in to establish a user session -
	 *              before navigating to the desired end point.
	 * 
	 *              - Logging navigation: If enabled in the configuration, it logs
	 *              the navigation action to the extent report, including the - URL
	 *              being navigated to.
	 * @param  endPoint The end point or relative URL of the web page to navigate to.
	 * @throws InterruptedException
	 */
	
	public void load(String endPoint) throws InterruptedException {
		// Construct the URL by combining the base URL and the specified endpoint
		String launch_URL = ConfigLoader.getInstance().getBaseUrl() + endPoint;
		driver.get(launch_URL);
		// Check if the user is on the logout page and re-login if necessary
		if (driver.getCurrentUrl().equalsIgnoreCase(ConfigLoader.getInstance().getBaseUrl()/* + "logout" */)) {
		//	new LoginPage(driver).doLogin();
			driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
		}
		logger.info(" URL Launched Successfully ---> : " + launch_URL);
		// Log the navigation action to the extent report if configured
		if (ConfigLoader.getInstance().getGenerateTestStepsOnReport().equalsIgnoreCase(YES)) {
			ExtentLogger.info(ICON_Navigate_Right + "  Navigating to : <b>" + ConfigLoader.getInstance().getBaseUrl()
					+ endPoint + "</b>");
		}
	}


	/**
	 * @author   : Palpandi
	 * @implNote : Click on a web element with the specified locator using an explicit wait strategy.`	
	 *           - This method is responsible for clicking on a web element with the provided 'by' locator using an explicit wait strategy specified by 'waitStrategy.' It also logs the action to the extent report if configured.
	 *           - The key tasks performed by this method include:
	 *           - Performing an explicit wait: It uses the 'ExplicitWaitFactory' to perform an explicit wait based on the provided 'waitStrategy' for the element to be clickable.
	 *           - Clicking on the element: Once the element becomes clickable, it is clicked.
	 *           - Logging the click action: If enabled in the configuration, it logs the element's name as a clickable action to the extent report.
	 * @param    : by The locator (By) of the web element to be clicked.
	 * @param    : waitStrategy The wait strategy to use for waiting until the element becomes clickable.
	 * @param    : elementName The name or description of the element being clicked.
	 */
	protected void click(By by, WaitStrategy waitStrategy, String elementName) {
		// Perform an explicit wait based on the provided wait strategy for the element
		// to be clickable
		ExplicitWaitFactory.performExplicitWait(waitStrategy, by).click();

		logger.info(elementName);
		// Log the click action to the extent report if configured
		if (ConfigLoader.getInstance().getGenerateTestStepsOnReport().equalsIgnoreCase(YES)) {	
			ExtentLogger.pass("<b>" + elementName + "</b> is clicked", true);
		}
	}


	/**
	 * @author : Palpandi
	 * @param  : hoverLocator The locator (By) of the element to hover over.
	 * @param  : clickLocator The locator (By) of the element to click after hovering.
	 * @param  : Perform a hover action over one element and then click on another element.
	 *
	 * This method is responsible for performing a hover action over the element specified by 'hoverLocator' and subsequently clicking 
	 * on another element specified by 'clickLocator.' It uses the Actions class to perform these actions.

	 * The key tasks performed by this method include:
	 * - Creating an Actions object: It creates an Actions object to enable the execution of hover and click actions.
	 * - Hovering over an element: It locates and hovers over the element specified by 'hoverLocator.'
	 * - Clicking on another element: After hovering, it locates and clicks on the element specified by 'clickLocator.'
	 */
	
	protected void hoverAndClick(By hoverLocator, By clickLocator) {
		Actions actions = new Actions(driver);
		WebElement elementToHover = driver.findElement(hoverLocator);
		actions.moveToElement(elementToHover).perform();
		waitForGivenTime(1);
		WebElement linkToClick = driver.findElement(clickLocator);
		linkToClick.click();
		logger.info("Locator has been clicked " + clickLocator);

	}

	
	/**
	 * @author : Palpandi
	 * @param  : By The locator (By) of the web element to which keys will be sent.
	 * @param  : sendKeyValue The value to be sent as input to the web element.
	 * @param  : waitStrategy The wait strategy to use for waiting until the element is visible.
	 * @param  : FieldName The name or description of the input field.
	 *
	 * Send input keys to a web element after clearing its content.
	 *
	 * This method is responsible for sending input keys to a web element specified by 'by' after clearing its content. It also logs t
	 * he action to the extent report if configured.

	 * The key tasks performed by this method include:
	 * - Clearing the input field: It clears the content of the input field specified by 'by' using the 'clear' method.
	 * 
	 * - Sending input keys: After clearing the field, it sends the 'sendKeyValue' as input to the web element using the 'sendKeys' 
	 * method.
	 * 
	 * - Logging the action: If enabled in the configuration, it logs a pass message to the extent report indicating that the provided 
	 * 'sendKeyValue' was entered successfully in the 'FieldName' input field.
	 */
	
	protected void sendKeys(By by, String sendKeyValue, WaitStrategy waitStrategy, String FieldName) {
		clear(by, WaitStrategy.VISIBLE, null);
		ExplicitWaitFactory.performExplicitWait(waitStrategy, by).sendKeys(sendKeyValue);
		logger.info(FieldName);

		if (ConfigLoader.getInstance().getGenerateTestStepsOnReport().equalsIgnoreCase(YES)) {
			ExtentLogger.pass("<b>" + FieldName + "</b> is entered successfully with value <b>" + sendKeyValue + "</b>", true);
		}
	}

	
	/**
	 * @author   : Palpandi
	 * @implNote : Clears the input field specified by the given By selector after waiting for the specified condition.
	 * @param    : by The By selector identifying the input field to be cleared.
	 * @param    : waitStrategy  The WaitStrategy to determine when the input field is ready for interaction.
	 * @param    : elementName   A descriptive name for the input field to be used in log messages.
	 */
	protected void clear(By by, WaitStrategy waitStrategy, String elementName) {
	    ExplicitWaitFactory.performExplicitWait(waitStrategy, by).clear();
	    logger.info(elementName);

	    // Check if test steps generation is enabled
	    if (ConfigLoader.getInstance().getGenerateTestStepsOnReport().equalsIgnoreCase(YES)) {
	        ExtentLogger.info("Clearing the field  <b>" + elementName + "</b>");
	    }
	}


	/**
	 * @author   : Palpandi
	 * @param    : by     - The By selector identifying the input field to be cleared and populated.
	 * @param    : value  - The value to be entered into the input field.
	 * @param    : waitStrategy -  The WaitStrategy to determine when the input field is ready for interaction.
	 * @param    : elementName  -  A descriptive name for the input field to be used in log messages.
	 */
	protected void clearAndSendKeys(By by, String value, WaitStrategy waitStrategy, String elementName) {
	    WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
	    element.clear();
	    element.sendKeys(value);

		logger.info(elementName);
		if (ConfigLoader.getInstance().getGenerateTestStepsOnReport().equalsIgnoreCase(YES)) {
			ExtentLogger.info("Clearing the field  <b>" + elementName + "</b> Add New value  " + value + "");
		}	                                            
	}
	
	

   /**
	* @author    - Palpandi
	* @implNote  - a file to the specified file input element after waiting for the specified condition.
	* @param     - fileInputId  - The By selector identifying the file input element where the file will be uploaded.
	* @param     - waitStrategy - The WaitStrategy to determine when the file input element is ready for interaction.
	* @param     - file - The path to the file that will be uploaded.
	* @throws    - IOException  If there is an issue with creating a temporary file or interacting with the file input.
    */
	protected void uploadFile(By fileInputId, WaitStrategy waitStrategy, String file) throws IOException {
	    // Create a temporary file (if necessary)
	    new FileCreator();
	    
	    // Locate the file input element and send the file's absolute path to it+
	    ExplicitWaitFactory.performExplicitWait(waitStrategy, fileInputId)
	            .sendKeys(FileCreator.create_New_Tempt_File(file).getAbsolutePath());
	    logger.info(" File Uploaded " +file);
	    // Check if test steps generation is enabled
	    if (ConfigLoader.getInstance().getGenerateTestStepsOnReport().equalsIgnoreCase(YES)) {
	        ExtentLogger.pass("<b>" + file + "</b> has been uploaded successfully to <b>" + fileInputId + "</b>", true);
	    }
	}


	/**
	 * @author Palpandi
	 * 
	 * Selects or deselects an option from a dropdown element based on the specified criteria after waiting for the specified condition.
	 *
	 * @param by            The By selector identifying the dropdown element.
	 * @param value         The value to select or deselect from the dropdown.
	 * @param waitStrategy  The WaitStrategy to determine when the dropdown element is ready for interaction.
	 * @param elementName   A descriptive name for the dropdown element to be used in log messages.
	 * @param selectType    The type of selection action to perform (e.g., select by index, value, or visible text).
	 * @param index         The index of the option to select or deselect (used when 'selectType' is selectByIndex or deselectByIndex).
	 * @throws IllegalArgumentException If the 'selectType' is invalid or if the provided parameters are invalid.
	 */
	protected void selectFromDropdown(By by, String value, WaitStrategy waitStrategy, String elementName,
	        DropdownType selectType, Integer index) {
	    // Locate the dropdown element
	    WebElement dropdownElement = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
	    Select dropdown = new Select(dropdownElement);

	    // Perform the selection or deselection action based on the 'selectType'
	    switch (selectType) {
	        case selectByIndex:
	            dropdown.selectByIndex(index);
	            break;
	        case selectByValue:
	            dropdown.selectByValue(value);
	            break;
	        case selectByVisibleText:
	            dropdown.selectByVisibleText(value);
	            break;
	        case deselectAll:
	            dropdown.deselectAll();
	            break;
	        case deselectByIndex:
	            dropdown.deselectByIndex(index);
	            break;
	        case deselectByValue:
	            dropdown.deselectByValue(value);
	            break;
	        case deselectByVisibleText:
	            dropdown.deselectByVisibleText(value);
	            break;
			default:
				throw new IllegalArgumentException("Invalid selectType: " + selectType + " or Invalid Parameters - "
						+ "Given Index Value = " + index + ", Given String Value = " + value);
	    }

	    logger.info( value + " is selected successfully in " + elementName );
	}



	
   /**
	* @author Palpandi Captures a screenshot of the current state of the
	*         application and logs it to the test report.
	*/
	protected void captureScreenshot() {

		logger.info(" Current Screen Capturing ");
		ExtentManager.getExtentTest().info("Capturing Screenshot",
				MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
	}


	/**
	 * @author Palpandi
	 * Pauses the test execution for a predefined amount of time (WAIT seconds).
	 */
	protected void waitForSomeTime() {
	
		logger.info(" Page action is waiting for some time.....! " );
	    Uninterruptibles.sleepUninterruptibly(WAIT, TimeUnit.SECONDS);
	}

  
	/**
	 * @author Palpandi
	 * @param Time The time in seconds to wait.
	 * @implSpec Pauses the test execution for a specified amount of time in
	 *           seconds.
	 */

	protected void waitForGivenTime(long time) {
		logger.info(" Page Action is waiting for : " + time);
		Uninterruptibles.sleepUninterruptibly(time, TimeUnit.SECONDS);
	}

	/**
	 * @author Palpandi Checks if an element identified by the given By selector is
	 *         displayed within a short waiting period.
	 * @param locator The By selector identifying the element to check for
	 *                visibility.
	 * @return true if the element is displayed, false otherwise.
	 */

	public boolean isDisplayed(By locator) {
		try {
			WebElement loc = waitShort.until(ExpectedConditions.presenceOfElementLocated(locator));
			ScrollUtils.scrollToElementPosition(locator);
			boolean status = loc.isDisplayed();
			logger.info(" Element is visible " + locator);
			return status;
		} catch (NoSuchElementException | TimeoutException e) {
			logger.info(" Element is not visible " + locator);
			return false;
		}
	}

	
   /**
	* @author Palpandi
	* @implNote :  if an element identified by the given By selector is selected (e.g., for checkboxes, radio buttons).
	* @param locator The By selector identifying the element to check for selection.
	* @return true if the element is selected, false otherwise.
	*/
	protected boolean isSelected(By locator) {
		try {
			ScrollUtils.scrollToElementPosition(locator);
			logger.info(" Element is already selected " + locator);
			return waitShort.until(ExpectedConditions.presenceOfElementLocated(locator)).isSelected();
		} catch (NoSuchElementException e) {
			logger.info(" Element is not selected " + locator);
			return false;
		}
	}

	
	/**
	 * @author Palpandi
	 * @implNote : if an element identified by the given By selector is Enabled  (e.g., for buttons).
	 * @param locator The By selector identifying the element to check for Enabled.
	 * @return true if the element is Enabled, false otherwise.
	 */
	protected boolean isEnabled(By locator) {
		try {
			ScrollUtils.scrollToElementPosition(locator);
			logger.info(" Element is already Enabled " + locator);
			return waitShort.until(ExpectedConditions.presenceOfElementLocated(locator)).isEnabled();
		} catch (NoSuchElementException e) {
			logger.info(" Element is not Enabled " + locator);
			return false;
		}
	}

	
	/**
	 * @author Palpandi Enables a toggle button identified by the specified spanPath
	 *         and inputPath elements if it is not already enabled.
	 * @param spanPath  The By selector identifying the span element.
	 * @param inputPath The By selector identifying the input element.
	 * @throws InterruptedException
	 */

	protected void enableToggle(By spanPath, By inputPath) throws InterruptedException {
		if (driver.findElement(inputPath).isSelected()) {
			logger.info("Toggle is already in enable state");
		} else if (!driver.findElement(inputPath).isSelected()) {
			click(spanPath, WaitStrategy.CLICKABLE, "Toggle Button Enabled");
			Thread.sleep(3000);
		}
	}


	/**
	 * @author Palpandi Disables a toggle button identified by the specified
	 *         spanPath and inputPath elements if it is not already disabled.
	 * @param spanPath  The By selector identifying the span element.
	 * @param inputPath The By selector identifying the input element.
	 * @throws InterruptedException
	 */
	protected void disableToggle(By spanPath, By inputPath) throws InterruptedException {
		waitShort.until(ExpectedConditions.presenceOfElementLocated(inputPath));
		if (!driver.findElement(inputPath).isSelected()) {
			logger.info("Toggle is already in disabled state");
		} else if (driver.findElement(inputPath).isSelected()) {
			click(spanPath, WaitStrategy.CLICKABLE, "Toggle Button Disabled");
			Thread.sleep(3000);
		}
	}

   /**
	* @author Palpandi
	* @Usage  Finds a WebElement with retry using a specified number of attempts and returns it.
	* @param  driver The WebDriver instance to use for locating the element.
	* @param  locator The By selector identifying the element to locate.
	* @param  maxAttempts The maximum number of attempts to locate the element.
	* @return The located WebElement or null if it cannot be found within the specified number of attempts.
	*/
	protected WebElement findElementWithRetry(WebDriver driver, By locator, int maxAttempts) {
		int attempts = 0;
		WebElement element = null;
		while (attempts < maxAttempts) {
			try {
				element = driver.findElement(locator);
				break;
			} catch (StaleElementReferenceException e) {
				attempts++;
			}
		}
		return element;
	}

	
   /**
	* @author   Palpandi 
	* @implSpec and returns console error messages from the WebDriver logs.
	* @return   The console error message, or null if no error messages are found.
	*/
	public Set<String> processErrorLogEntries() {

		LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
		Pattern stylePattern = Pattern.compile(".*Refused to apply style.*");
		Pattern scriptPattern = Pattern.compile(".*Refused to load the script.*");

		// Iterate through logs and handle errors
		Set<String> errorMessages = new HashSet<>();
		for (LogEntry entry : logEntries) {
			if (entry.getLevel().toString().equals("SEVERE")) {
				String errorMessage = entry.getMessage();
				if (stylePattern.matcher(errorMessage).find() || scriptPattern.matcher(errorMessage).find()) {
					continue; // Skip this error
				}
				// Handle the error
				System.out.println("[ERROR] " + errorMessage);
				// errorMessage1
				errorMessages.add(errorMessage);
			}
		}
		return errorMessages;
	}

   /**
	* @author Palpandi
	* @Usage  Checks if an element identified by the provided By selector exists on the page.
	* @param  by The By selector identifying the element to check for existence.
	* @return true if the element exists, false otherwise.
	*/
	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			logger.info("Element is present on the page      : " + by);
			return true;
		} catch (NoSuchElementException e) {
			logger.info("Element is not present on the page  : " + by);
			return false;
		}
	}


   /**
	* @author Palpandi
	* @Usage Simulates hovering the mouse pointer over an element identified by the provided By selector.
	* @param hoverPath The By selector identifying the element to hover over.
	*/
	protected void hoverOnElement(By hoverPath) {
		try {
			WebElement element = driver.findElement(hoverPath);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
			logger.info("Element has been hovered: " + hoverPath);
		} catch (NoSuchElementException e) {
			logger.info("Element not found for hover: " + hoverPath);
		} catch (Exception e) {
			logger.info("An error occurred while hovering over the element: " + hoverPath + e);
		}
	}

	
	/**
	 * @author Palpandi 
	 * @Usage Simulates a click-and-drag operation from a source element to a target element identified by their respective By selectors.
	 * @param source        The By selector identifying the source element.
	 * @param target        The By selector identifying the target element.
	 * @param waitStrategy  The WaitStrategy to determine when the elements are ready for interaction.
	 */
	protected void clickAndDrag(By source, By target, WaitStrategy waitStrategy) {
	    Actions actions = new Actions(driver);
	    actions.clickAndHold(ExplicitWaitFactory.performExplicitWait(waitStrategy, source))
	            .moveToElement(ExplicitWaitFactory.performExplicitWait(waitStrategy, target))
	            .moveByOffset(0, 50)
	            .build()
	            .perform();
	    logger.info(" Element has been clicked : -> "+source+ " and dragged to -> " +target);
	}

	
	/**
	 * @author Palpandi
	 * @Usage Retrieves and returns the value of a specified attribute of an element identified by the provided locator.
	 * @param locator       The By selector identifying the element.
	 * @param AttributeName The name of the attribute to retrieve.
	 * @return The value of the specified attribute.
	 */
	public String getAttribute(By locator, String AttributeName) {
	    WebElement element = driver.findElement(locator);
	    String attributeName = element.getAttribute(AttributeName);
	    logger.info(" Captured attribute name is -> "+attributeName);
	    return attributeName;
	}

	
	/**
	 * @author Palpandi
	 * @Usage Simulates a click-and-drag operation by specifying an offset from the source element.
	 * @param source        The By selector identifying the source element.
	 * @param xOffset       The horizontal offset for the drag operation.
	 * @param yOffset       The vertical offset for the drag operation.
	 * @param waitStrategy  The WaitStrategy to determine when the source element is ready for interaction.
	 */
	protected void clickAndDragByOffset(By source, int xOffset, int yOffset, WaitStrategy waitStrategy) {
	    Actions actions = new Actions(driver);
	    actions.clickAndHold(ExplicitWaitFactory.performExplicitWait(waitStrategy, source))
	            .moveByOffset(xOffset, yOffset)
	            .release()
	            .build()
	            .perform();
	    logger.info("Click performed on -> "+source +" and moved to this offset "+xOffset+" , "+yOffset);
	}

	
	/**
	 * @author Palpandi
	 * @Usage Saves a key-value pair to the specified properties file.
	 * @param configPath The PropertyLoader instance representing the path to the properties file.
	 * @param title      The key to be saved.
	 * @param value      The value associated with the key.
	 */
	public void saveOnProperties(PropertyLoader configPath, String title, String value) {
	    new Helper().saveOnProperties(configPath, title, value);
	}

 
	/**
	 * Gets the value associated with a given property name from the specified properties file.
	 *
	 * @param configPath   The PropertyLoader instance representing the path to the properties file.
	 * @param propertyName The name of the property whose value is to be retrieved.
	 * @return The value associated with the given property name.
	 * @throws IOException If there is an issue reading the properties file.
	 */
	public String getValueFromProperty(PropertyLoader configPath, String propertyName) throws IOException {
		try {
			return new Helper().getValueFromProperty(configPath, propertyName);
		} catch (IOException e) {
			throw new IOException("Error getting value from property", e);
		}
	}


}
