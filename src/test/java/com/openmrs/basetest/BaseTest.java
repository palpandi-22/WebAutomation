package com.openmrs.basetest;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.openmrs.constants.FrameworkConstants;
import com.openmrs.driver.DriverManager;
import com.openmrs.driver.DriverManagerFactory;
import com.openmrs.enums.DriverType;
import com.openmrs.enums.PropertyLoader;



public class BaseTest {

	
	protected WebDriverWait wait ;
	ITestResult result;
	protected int executedCount= 1;
	protected int beforeMethod = 0;
	private static final Logger logger = Logger.getLogger(BaseTest.class.getName());

	
	private void setDriver(WebDriver driver) {
		DriverManager.setDriver(driver);
	}

	protected WebDriver getDriver() {
		return DriverManager.getDriver();
	}
	
	
	@BeforeSuite(enabled = false)
	public synchronized void init_Browser(@Optional String browser ) throws Exception
	{
		 /**
	     * Sets the browser to be used for the test.
	     * @param browser The browser specified for the test, or the default browser if not provided.
	     * @return The selected browser for the test.
	     */
	    browser = setBrowserValue(browser);

	    /**
	     * Initializes the WebDriver based on the selected browser type.
	     * @param browser The browser for which the WebDriver should be created.
	     */
	    setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
	    getDriver().manage().window().maximize();

	    /**
	     * Sets up a WebDriverWait with the explicit wait duration specified in the FrameworkConstants.
	     * @param getDriver() The WebDriver instance for which the WebDriverWait is created.
	     * @param Duration.ofSeconds(FrameworkConstants.getExplicitWait()) The duration for the explicit wait.
	     */
	    wait = new WebDriverWait(getDriver(), Duration.ofSeconds(FrameworkConstants.getExplicitWait()));
		
		// main_Login();
		
		logger.info(" Test cases going to execute...");
		getDriver().quit();
		
	}

 
	
	/*
	 * @Optional -> You can run the test case individually directly from Java class
	 */
	/**
	 * @author Palpandi
	 * BeforeMethod method to start the web driver, configure the test environment, and set up necessary preparations for test 
	 * execution.
	 *
	 * This method is executed before each test method and plays a crucial role in preparing the test environment. It accepts an 
	 * optional 'browser' parameter specified using TestNG's {@code @Parameters} annotation, allowing flexibility in choosing the 
	 * browser for testing.

	 * The key tasks performed by this method include:
	 * - Starting the web driver: It initializes the web driver for the selected browser type, enabling the execution of test steps.
	 * - Printing method information: It prints information about the currently executed test method, providing visibility into the 
	 *   test suite's progress.
	 * - Assigning the web driver: The appropriate web driver instance is assigned for the test method, allowing interaction with 
	 *   the browser.
	 * - Setting up test configurations: Various test configurations, such as explicit wait times, are initialized to ensure proper 
	 *   synchronization during test execution.
	 * - Handling cookies: Based on the test context, this method sets cookies in the browser session to maintain a consistent state
         between test cases.
	 * - Enabling modules: It checks for test method annotations and enables modules as specified by the annotations, based on the 
	 *   provided business logic.

	 * The logic related to setting cookies is as follows:
	 * - If the value of 'beforeMethod' is less than 20 (indicating the first 20 test methods), it sets cookies using a predefined 
	 *   set 'Cookies'.
	 * - If 'beforeMethod' is greater than or equal to 20 (indicating subsequent test methods), it retrieves cookies from the browser 
	 *   session using 'get_Cookie_From_Browser' method and sets them.

	 * @param browser An optional parameter specifying the desired browser for testing. If provided, it determines the type of browser 
	 *   to use for the current test. If not specified, it may use a default browser.
	 * @param method The test method for which the web driver is started. This allows for method-specific configurations and reporting.
	 * @throws InterruptedException If thread execution is interrupted during driver setup.
	 * @throws IOException If an I/O exception occurs during the handling of cookies or other test configurations.
	 */
	@Parameters("browser")
	@BeforeMethod
	public synchronized void startDriver(@Optional String browser ,Method method) throws InterruptedException, IOException {
		
		beforeMethod++;
		browser = setBrowserValue(browser);
		setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
	    getDriver().manage().window().maximize();

		System.out.println("################################################################################################");
		System.out.println("                                                                                                ");
		System.out.println("                                                                                                ");
		System.out.println("                                                                                                ");
		System.out.println(                                  method.getName().toUpperCase()                                  );
		System.out.println("                                                                                                ");
		System.out.println("                                                                                                ");
		System.out.println("                                                                                                ");
		System.out.println("################################################################################################");
		System.out.println("                                                                                                ");
		System.out.println("                                 TEST CASE EXECUTION HAS STARTED                                ");
		System.out.println("                                                                                                ");
		System.out.println("################################################################################################");
        
		// need to handle the login method call this place
		executedCount++;
	}



	/**
	 * @author Palpandi
	 * AfterMethod method to quit the web driver and perform post-test cleanup.
	 *
	 * This method is executed after each test method and is responsible for quitting the web driver to release browser resources. 
	 *   It also performs any necessary post-test cleanup. An optional 'browser' parameter specified using TestNG's {@code @Parameters}
	 *   annotation is accepted, allowing flexibility in specifying the browser for testing.

	 * The key tasks performed by this method include:
	 * - Quitting the web driver: It closes the browser and releases associated resources if the web driver instance is not null.
	 * - Printing end of execution information: It displays end-of-execution messages and separators for visibility in the test 
	 *   suite's progress.

	 * @param browser An optional parameter specifying the browser used for testing. If provided, it determines the type of browser
	 *   used for the current test. If not specified, it may use a default browser.
	 * @param result The result of the test method, including test status and other information.
	 * @throws IOException If an I/O exception occurs during post-test cleanup or reporting.
	 */
	@AfterMethod
	public synchronized void quitDriver(@Optional String browser, ITestResult result) throws IOException {
		// Quit the web driver to release browser resources if the driver instance is
		// not null
		try {
			if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP
					|| result.getStatus() == ITestResult.SUCCESS) {
				if (getDriver() != null) {
					getDriver().quit();
				}
			}
		} finally {
			getDriver().quit();

		}

		// Display end-of-execution messages and separators for visibility
		System.out.println("################################################################################################");
		System.out.println("                                                                                                ");
		System.out.println("                                            END                                                 ");
		System.out.println("                                                                                                ");
		System.out.println("################################################################################################");
	}


	
	@AfterSuite
	public synchronized void deleteAccount(@Optional String browser, ITestResult result)	{
	
		getDriver().quit();

	}

	
	
	
	/**
	 * 
	 * @author Palpandi
	 * 
	 * Set the browser value based on the specified parameter or system property.
	 *
	 * This method is responsible for determining the browser to be used for testing by checking a specified 'browser' parameter or a system property ('browser').

	 * The key tasks performed by this method include:
	 * - Checking the 'browser' parameter: It checks if a 'browser' parameter has been specified. If not, it sets the 'browser' to a default value, typically 'CHROME'.
	 * - Handling test execution information: It provides information about the test execution method. If 'browser' was not provided as a parameter, it indicates that test execution was not done through Maven command line or a TestNG XML file.
	 * - Checking system property: It checks for a 'browser' system property, which can be used to specify the browser type when running tests from Maven or a TestNG XML file.

	 * @param browser The browser value specified as a parameter or system property, or the default 'CHROME' browser if not provided.
	 * @return The determined browser value for testing.
	 */
	private String setBrowserValue(String browser) {
	    // Check if a 'browser' parameter has been specified
	    if (browser == null) {
	        // Set the 'browser' to a default value and provide test execution information
	        browser = "CHROME";
	        System.out.println("################################################################################################");
	        System.out.println(                                                                                                  );
	        System.out.println("			                                                                                    ");
	        System.out.println(                                                                                                  );
	        System.out.println("    TEST EXECUTION NOT DONE BY MAVEN CMD OR TESTNG.XML FILE -> SETTING THE VALUE : " + browser   );
	        System.out.println(                                                                                                  );
		    System.out.println("                             CURRENT RUNNING BROWSER : " + browser                               );
	        System.out.println(                                                                                                  );
	        System.out.println("################################################################################################");
	        System.out.println();
	    } else if (browser != null) {
	        // Check for a 'browser' system property and use it if provided
	        browser = System.getProperty("browser", browser);
	    }

	    return browser;
	}


	/**
	 * @author Palpandi
	 * @implNote the web page to the position of a specific element.
	 *
	 * @implSpec method is responsible for scrolling the web page to the position of
	 *           a specified element. It uses JavaScript-based scrolling to bring
	 *           the element into view. The 'positionElement' parameter represents
	 *           the target element that you want to scroll to.
	 * 
	 *           The key tasks performed by this method include: - Locating the
	 *           target element: It finds the target element on the web page based
	 *           on the provided locator. - Scrolling to the element's location: It
	 *           utilizes JavaScript to scroll the web page, ensuring that the
	 *           target element is visible within the viewport. - Optionally
	 *           offsetting the scroll position: You can adjust the scroll position
	 *           to prevent the element from being covered, such as by scrolling up
	 *           by a specified number of pixels.
	 * 
	 * @param positionElement The locator (By) of the element to which the web page
	 *                        should be scrolled.
	 */
	public void scrollPageToElementPosition(By positionElement) {
	    // Find the target element on the web page
	    WebElement element = getDriver().findElement(positionElement);

	    // Scroll the web page to bring the target element into view
	    ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", element);

	    // Optionally offset the scroll position (e.g., scrolling up by 100 pixels)
	    ((JavascriptExecutor) getDriver()).executeScript("window.scrollBy(0, -100);");
	}

	public String getValueFromProperty(PropertyLoader configPath, String propertyName) throws IOException {
		Properties prop;
		prop = getPropertyAccess(configPath.getValue());
		return prop.getProperty(propertyName);
	}
	
	public Properties getPropertyAccess(String configPath) throws IOException {
		Properties prop = new Properties();
		FileInputStream path = new FileInputStream(configPath);
		prop.load(path);
		return prop;
	}


}
